package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.CommentDao;
import com.ssafy.pistachio.model.dao.FeedDao;
import com.ssafy.pistachio.model.dao.FeedPictureDao;
import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.comment.request.AddCommentRequest;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponseAll;
import com.ssafy.pistachio.model.dto.feed.request.PictureRequest;
import com.ssafy.pistachio.model.dto.user.response.UserResponse;
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

    public FeedServiceImpl(FeedDao feedDao,
                           UserDao userDao,
                           CommentDao commentDao, FeedPictureDao feedPictureDao) {
        this.feedDao = feedDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.feedPictureDao = feedPictureDao;
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
        return (int) feedId;
    }

    @Override
    public List<FeedResponseAll> getAll() {
        List<Map<String, Object>> results = feedDao.selectAll();

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
    public FeedResponse getOne(long feedId) {
        Feed feed = feedDao.getFeed(feedId);
        if (feed == null) {
            return null;
        }

        List<FeedPicture> feedPictures = feedDao.getPictures(feedId);
        List<CommentResponse> feedComments = commentDao.getCommentByFeedId(feedId);

        return FeedResponse.builder()
                .feed(feed)
                .feedPictures(feedPictures)
                .feedComment(feedComments)
                .userResponse(userDao.getUserByIdForResponse(feed.getUserId()))
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
}
