package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.feed.Feed;

import java.util.List;

public interface FeedService {

    // 피드 작성
    public int writeFeed(Feed feed);

    // 피드 전체 조회
    public List<Feed> getAll();

    // 피드 상세 조회
    public Feed getOne(int feedId);

    // 피드 수정
    public int modifyFeed(Feed feed);

    // 피드 삭제
    public int deleteFeed(int feedId);
}
