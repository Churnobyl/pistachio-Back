package com.ssafy.pistachio.model.dto.comment.request;

public class AddCommentRequest {
    private Long feedId;
    private Long commentUserNo;
    private String content;

    public AddCommentRequest(Long feedId,
                             Long commentUserNo,
                             String content) {
        this.feedId = feedId;
        this.commentUserNo = commentUserNo;
        this.content = content;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public Long getCommentUserNo() {
        return commentUserNo;
    }

    public void setCommentUserNo(Long commentUserNo) {
        this.commentUserNo = commentUserNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "AddCommentRequest{" +
                "feedId=" + feedId +
                ", commentUserNo=" + commentUserNo +
                ", content='" + content + '\'' +
                '}';
    }
}
