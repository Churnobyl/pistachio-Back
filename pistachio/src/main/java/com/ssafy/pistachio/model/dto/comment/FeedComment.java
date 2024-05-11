package com.ssafy.pistachio.model.dto.comment;

import java.util.Date;

/**
 * 피드 댓글
 */
public class FeedComment {
    Long id;
    Long feedId;
    Long commentUserNo;
    String content;
    Date createdTime;

    public FeedComment(Long id,
                       Long feedId,
                       Long commentUserNo,
                       String content,
                       Date createdTime) {
        this.id = id;
        this.feedId = feedId;
        this.commentUserNo = commentUserNo;
        this.content = content;
        this.createdTime = createdTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public String toString() {
        return "FeedComment{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", commentUserNo=" + commentUserNo +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
