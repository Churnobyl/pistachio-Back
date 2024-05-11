package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.feed.Feed;

import java.util.List;

public interface FeedDao {

    // 피드 작성
    public int createFeed(Feed feed);

    // 피드 전체 조회
    public List<Feed> selectAll();

    // 피드 상세 조회
    public Feed getFeed(int feedId);

    // 피드 수정
    public int updateFeed(Feed feed);

    // 피드 삭제
    public int deleteFeed(int feedId);
}
