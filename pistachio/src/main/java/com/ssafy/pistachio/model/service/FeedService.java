package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.comment.request.AddCommentRequest;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponseAll;
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
    public int modifyFeed(long feedId, FeedRequest feedRequest);

    // 피드 삭제
    public int deleteFeed(long feedId);

    // 댓글 작성
    public int writeComment(AddCommentRequest addCommentRequest);

    // 댓글 하나 보기
    public CommentResponse readComment(long commentId);

    // 댓글 삭제
    public int deleteComment(long commentId);
}
