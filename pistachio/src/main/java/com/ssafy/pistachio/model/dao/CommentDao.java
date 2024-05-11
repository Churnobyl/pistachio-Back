package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.comment.FeedComment;

import java.util.List;

public interface CommentDao {

    // 댓글 작성
    public int createComment(FeedComment comment);

    // 댓글 보기
    public List<FeedComment> getCommentByFeedId(int feedId);

    // 댓글 삭제
    public int deleteComment(int commentId);
}
