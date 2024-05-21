package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedLike;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.request.PictureRequest;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface FeedDao {

    // 피드 작성
    public int createFeed(FeedRequest feedRequest);

    // 사진 추가
    public int addPicturesToFeed(List<PictureRequest> pictures);

    // 피드 전체 조회
//    @Select("SELECT f.id AS feed_id, f.user_id, f.project_id, f.content, " +
//            "fl.id AS feed_like_id, fl.user_id AS like_user_id, " +
//            "fp.id AS feed_picture_id, fp.url AS feed_picture_url, " +
//            "fc.id AS feed_comment_id, fc.content AS feed_comment_content " +
//            "FROM feed f " +
//            "LEFT JOIN feed_like fl ON f.id = fl.feed_id " +
//            "LEFT JOIN feed_picture fp ON f.id = fp.feed_id " +
//            "LEFT JOIN feed_comment fc ON f.id = fc.feed_id")
    public List<Map<String, Object>> selectAll();

    // 피드 상세 조회
    public Feed getFeed(long feedId);

    // 피드 사진 조회
    public List<FeedPicture> getPictures(long feedId);

    // 피드 좋아요 조회
    public FeedLike getLikesCount(long feedId);

    // 피드 댓글 조회
    public List<FeedComment> getComments(long feedId);

    // 피드 수정
    int updateFeedContent(long feedId, String content);

    // 피드 삭제
    public int deleteFeed(long feedId);
}
