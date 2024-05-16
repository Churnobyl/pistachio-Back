package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.request.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.request.FeedResponseAll;
import com.ssafy.pistachio.s3.S3FileDto;

import java.util.List;

public interface FeedService {

    // 피드 작성
    public int writeFeed(FeedRequest feedRequest, List<S3FileDto> pictures);

    // 피드 전체 조회
    public List<FeedResponseAll>  getAll();

    // 피드 상세 조회
    public FeedResponse getOne(long feedId);

    // 피드 수정
    public int modifyFeed(Feed feed);

    // 피드 삭제
    public int deleteFeed(int feedId);
}
