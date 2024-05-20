package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.FeedDao;
import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.request.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.request.FeedResponseAll;
import com.ssafy.pistachio.model.dto.feed.request.PictureRequest;
import com.ssafy.pistachio.s3.S3FileDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {

    private final FeedDao feedDao;
    private final UserDao userDao;

    public FeedServiceImpl(FeedDao feedDao,
                           UserDao userDao) {
        this.feedDao = feedDao;
        this.userDao = userDao;
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

            builder.user(userDao.getUserById((Long) row.get("user_id")));

            if (row.get("feed_picture_id") != null) {
                builder.feedPictures(new FeedPicture(((Number) row.get("feed_picture_id")).longValue(), feedId, (String) row.get("feed_picture_url")));
            }

            if (row.get("feed_comment_id") != null) {
                builder.feedComment(new FeedComment(((Number) row.get("feed_comment_id")).longValue(), (String) row.get("feed_comment_content")));
            }
        }

        return feedResponseMap.values().stream()
                .map(FeedResponseAll.Builder::build)
                .collect(Collectors.toList());
    }

    @Override
    public FeedResponse getOne(long feedId) {
        Feed feed = feedDao.getFeed(feedId);
        if (feed == null) {
            return null;
        }

        List<FeedPicture> feedPictures = feedDao.getPictures(feedId);
        List<FeedComment> feedComments = feedDao.getComments(feedId);

        return FeedResponse.builder()
                .feed(feed)
                .feedPictures(feedPictures)
                .feedComment(feedComments)
                .build();
    }

    @Override
    public int modifyFeed(Feed feed) {
        return feedDao.updateFeed(feed);
    }

    @Override
    public int deleteFeed(int feedId) {
        return feedDao.deleteFeed(feedId);
    }
}
