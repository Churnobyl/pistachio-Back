package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedLike;

import java.util.List;

public interface FeedLikeDao {

    // 피드 좋아요
    public int insertFeedLike(FeedLike feedLike);

    // 피드 좋아요 취소
    public int deleteFeedLike(FeedLike feedLike);

    // 각 피드 당 전체 좋아요
    public int selectAllFeedLikes(Long feedId);

    // 각 유저의 좋아요한 게시물 전체
    public List<Feed> selectFeedLikes(Long userId);
}
