package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.comment.request.AddCommentRequest;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponse;

import java.util.List;

public interface CommentDao {

    // 댓글 작성
    public int createComment(AddCommentRequest addCommentRequest);

    // 댓글 하나 가져오기
    public CommentResponse getOneCommentByCommentId(long commentId);

    // 댓글 보기
    public List<CommentResponse> getCommentByFeedId(long feedId);

    // 댓글 삭제
    public int deleteComment(long commentId);
}
