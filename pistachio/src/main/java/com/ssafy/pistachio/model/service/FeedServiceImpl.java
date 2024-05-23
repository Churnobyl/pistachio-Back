package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.*;
import com.ssafy.pistachio.model.dto.comment.request.AddCommentRequest;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.donate.response.DonateProjectResponse;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.request.PictureRequest;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponseAll;
import com.ssafy.pistachio.model.dto.feed.response.InterestResponse;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.s3.S3FileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedDao feedDao;
    private final UserDao userDao;
    private final CommentDao commentDao;
    private final FeedPictureDao feedPictureDao;
    private final FeedLikeDao feedLikeDao;
    private final InterestingProjectDao interestingProjectDao;
    private final DonationDao donationDao;

    private final Long NORMAL_PROJECT_ID = 1L;

    public FeedServiceImpl(FeedDao feedDao,
                           UserDao userDao,
                           CommentDao commentDao,
                           FeedPictureDao feedPictureDao,
                           FeedLikeDao feedLikeDao,
                           InterestingProjectDao interestingProjectDao,
                           DonationDao donationDao) {
        this.feedDao = feedDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.feedPictureDao = feedPictureDao;
        this.feedLikeDao = feedLikeDao;
        this.interestingProjectDao = interestingProjectDao;
        this.donationDao = donationDao;
    }

    @Transactional
    @Override
    public int writeFeed(FeedRequest feedRequest, List<S3FileDto> pictures) {
        feedDao.createFeed(feedRequest);

        long feedId = feedRequest.getId();

        // 사진 없을 경우 제한
        if (pictures.isEmpty()) {
            return 0;
        }

        List<PictureRequest> picturesIdMounted = new ArrayList<>();

        for (S3FileDto picture : pictures) {
            String url = picture.getUploadFilepath() + "/" + picture.getUploadFileName();
            PictureRequest pictureRequest = new PictureRequest(feedId, url);
            picturesIdMounted.add(pictureRequest);
        }

        feedDao.addPicturesToFeed(picturesIdMounted);

        if (feedRequest.isBoast()) {
            donationDao.updateBoast(feedRequest);
        }

        return (int) feedId;
    }

    @Transactional
    public List<FeedResponseAll> getAll(Long userId) {
        List<Long> likedFeedIds = feedLikeDao.getLikedFeedIdsByUserId(userId);
        List<Long> followingUserIds = userDao.getFollowerIdByUserId(userId);
        return processFeeds(userId,
                feedDao.selectAll(),
                likedFeedIds,
                followingUserIds);
    }

    @Transactional
    public List<FeedResponseAll> getAllByUser(Long userId) {
        List<Long> likedFeedIds = feedLikeDao.getLikedFeedIdsByUserId(userId);
        List<Long> followingUserIds = userDao.getFollowerIdByUserId(userId);
        return processFeeds(userId,
                feedDao.selectAllByUser(userId),
                likedFeedIds,
                followingUserIds);
    }

    private List<FeedResponseAll> processFeeds(Long userId,
                                               List<Map<String, Object>> results,
                                               List<Long> likedFeedIds,
                                               List<Long> followingUserIds) {
        if (results == null) {
            return null;
        }

        Map<Long, FeedResponseAll.Builder> feedResponseMap = new ConcurrentHashMap<>();

        for (Map<String, Object> row : results) {
            Long feedId = ((Number) row.get("feed_id")).longValue();

            FeedResponseAll.Builder builder = feedResponseMap.computeIfAbsent(feedId, id ->
                    FeedResponseAll.builder()
                            .feed(new Feed(feedId,
                                    (Long) row.get("user_id"),
                                    (Long) row.get("project_id"),
                                    (String) row.get("content"),
                                    Long.valueOf((Integer) row.get("like_cnt")),
                                    row.get("is_boast").equals("1"),
                                    java.sql.Timestamp.valueOf((LocalDateTime) row.get("created_time")),
                                    java.sql.Timestamp.valueOf((LocalDateTime) row.get("updated_time"))))
                            .isUserLike(likedFeedIds.contains(feedId))
                            .isUserFollow(followingUserIds.contains((Long) row.get("user_id")))
            );

            builder.userResponse(userDao.getUserByIdForResponse((Long) row.get("user_id")));

            if (row.get("feed_picture_id") != null) {
                FeedPicture feedPicture = new FeedPicture(
                        ((Number) row.get("feed_picture_id")).longValue(),
                        feedId,
                        (String) row.get("feed_picture_url")
                );
                if (!builder.build().getFeedPictures().contains(feedPicture)) {
                    builder.feedPictures(feedPicture);
                }
            }

            if (row.get("feed_comment_id") != null) {
                CommentResponse commentResponse = new CommentResponse(
                        ((Number) row.get("feed_comment_id")).longValue(),
                        feedId,
                        (String) row.get("feed_comment_content"),
                        ((Number) row.get("feed_comment_user_id")).longValue(),
                        (String) row.get("username"),
                        (String) row.get("userProfile")
                );
                if (!builder.build().getCommentResponses().contains(commentResponse)) {
                    builder.commentResponses(commentResponse);
                }
            }
        }

        List<FeedResponseAll> responseList = feedResponseMap.values().stream()
                .map(FeedResponseAll.Builder::build)
                .collect(Collectors.toList());

        Collections.reverse(responseList);

        return responseList;
    }

    @Override
    public FeedResponse getOne(long feedId, Long userId) {
        Feed feed = feedDao.getFeed(feedId);
        if (feed == null) {
            return null;
        }

        List<FeedPicture> feedPictures = feedDao.getPictures(feedId);
        List<CommentResponse> feedComments = commentDao.getCommentByFeedId(feedId);
        boolean isUserLike = feedLikeDao.getLikedFeedIdsByUserId(userId).contains(feedId);

        return FeedResponse.builder()
                .feed(feed)
                .feedPictures(feedPictures)
                .feedComment(feedComments)
                .userResponse(userDao.getUserByIdForResponse(feed.getUserId()))
                .isUserLike(isUserLike)
                .build();
    }

    @Override
    @Transactional
    public int modifyFeed(long feedId, FeedRequest feedRequest) {
        // 피드 내용 업데이트
        int result = feedDao.updateFeedContent(feedId, feedRequest.getContent());

        // 기존 이미지 URL을 모두 삭제
        feedPictureDao.deleteByFeedId(feedId);

        // 새로운 이미지 URL을 추가
        for (String url : feedRequest.getPictureUrls()) {
            feedPictureDao.insert(new FeedPicture(feedId, url));
        }

        return result;
    }

    @Override
    public int deleteFeed(long feedId) {
        return feedDao.deleteFeed(feedId);
    }

    @Override
    public int writeComment(AddCommentRequest addCommentRequest) {
        Feed feed = feedDao.getFeed(addCommentRequest.getFeedId());

        if (feed == null) {
            return 0;
        }

        return commentDao.createComment(addCommentRequest);
    }

    @Override
    public CommentResponse readComment(long commentId) {
        return commentDao.getOneCommentByCommentId(commentId);
    }

    @Override
    public int deleteComment(long commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Transactional
    @Override
    public void batchUpdateLikes(Long userId, Map<Long, Boolean> likeStatusMap) throws IllegalArgumentException {
        // 현재 유저의 모든 좋아요 상태를 가져옴
        List<Long> currentLikedPostIds = feedLikeDao.getLikedFeedIdsByUserId(userId);

        for (Map.Entry<Long, Boolean> entry : likeStatusMap.entrySet()) {
            Long feedId = entry.getKey();
            Boolean isLiked = entry.getValue();

            Feed feed = feedDao.getFeed(feedId);

            if (feed == null) {
                throw new IllegalArgumentException("피드가 없습니다.");
            }

            boolean currentlyLiked = currentLikedPostIds.contains(feedId);

            if (isLiked && !currentlyLiked) {
                // 좋아요를 추가
                feedLikeDao.insertLike(feedId, userId);
                feedLikeDao.addLikeCount(feedId);

                Long projectId = feed.getProjectId();
                Long currCount = interestingProjectDao.getCount(userId, projectId);

                if (Objects.equals(projectId, NORMAL_PROJECT_ID)) {
                    continue;
                } else

                if (currCount != null) {
                    interestingProjectDao.updateCount(userId, projectId, 1L);
                } else {
                    interestingProjectDao.insertInterestingProject(userId, projectId, 1L);
                }
            } else if (!isLiked && currentlyLiked) {
                // 좋아요를 삭제
                feedLikeDao.deleteLike(feedId, userId);
                feedLikeDao.subLikeCount(feedId);

                Long projectId = feed.getProjectId();
                Long currCount = interestingProjectDao.getCount(userId, projectId);

                if (Objects.equals(projectId, NORMAL_PROJECT_ID)) {
                    continue;
                }

                if (currCount != null) {
                    if (currCount == 1) {
                        interestingProjectDao.deleteInterestingProject(userId, projectId);
                    } else {
                        interestingProjectDao.updateCount(userId, projectId, -1L);
                    }
                }
            }
        }
    }

    @Override
    public List<InterestResponse> getInterestByUser(User dbUser) {
        int role = userDao.getRole(dbUser.getId());

        List<InterestResponse> interestResponses = new ArrayList<>();

        switch (role) {
            case 1: // 일반
                interestResponses = donationDao.selectInterestResponseByUserId(dbUser.getId());
                break;
            case 2: // 피스타치오
                Long projectId = donationDao.selectAffiliationByUserId(dbUser.getId());

                if (projectId == null) break;

                InterestResponse ir1 = new InterestResponse();
                ir1.setProjectId(projectId);
                ir1.setProjectName(donationDao.selectOneDonateProjectById(projectId).getName());
                interestResponses.add(ir1);
                break;
            case 3: // 기관
                List<DonateProjectResponse> donateProjectResponses
                        = donationDao.selectAllDonateProjectByAgencyId(dbUser.getMembershipId());

                for (DonateProjectResponse response : donateProjectResponses) {
                    InterestResponse ir2 = new InterestResponse();
                    ir2.setProjectId(response.getId());
                    ir2.setProjectName(response.getName());
                    interestResponses.add(ir2);
                }
                break;
        }

        return interestResponses;
    }
}
