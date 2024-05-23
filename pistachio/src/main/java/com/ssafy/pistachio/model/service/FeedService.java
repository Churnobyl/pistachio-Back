package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.comment.request.AddCommentRequest;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponseAll;
import com.ssafy.pistachio.model.dto.feed.response.InterestResponse;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.s3.S3FileDto;

import java.util.List;
import java.util.Map;

public interface FeedService {

    // 피드 작성
    public int writeFeed(FeedRequest feedRequest, List<S3FileDto> pictures);

    // 피드 전체 조회
    public List<FeedResponseAll>  getAll(Long userId);

    // 특정 유저 피드 전체 조회
    List<FeedResponseAll> getAllByUser(Long userId);

    // 피드 상세 조회
    public FeedResponse getOne(long feedId, Long userId);

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

    // 좋아요 업데이트
    void batchUpdateLikes(Long userId, Map<Long, Boolean> likeStatusMap);

    // 관심 프로젝트 얻기
    List<InterestResponse> getInterestByUser(User dbUser);
}
