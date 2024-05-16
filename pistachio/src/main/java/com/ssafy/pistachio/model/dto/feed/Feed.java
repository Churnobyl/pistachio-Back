package com.ssafy.pistachio.model.dto.feed;

import java.util.Date;

/**
 * 피드
 */
public class Feed {
    private Long id;
    private Long userId;
    private Long projectId;
    private String content;
    private Long likeCnt;
    private Boolean isBoast;
    private Date createdTime;
    private Date updatedTime;

    public Feed(Long id,
                Long userId,
                Long projectId,
                String content,
                Long likeCnt,
                Boolean isBoast,
                Date createdTime,
                Date updatedTime) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.content = content;
        this.likeCnt = likeCnt;
        this.isBoast = isBoast;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
    }

    public Feed(Long id, Long userId, Long projectId, String content) {
        this.id = id;
        this.userId = userId;
        this.projectId = projectId;
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(Long likeCnt) {
        this.likeCnt = likeCnt;
    }

    public Boolean getBoast() {
        return isBoast;
    }

    public void setBoast(Boolean boast) {
        isBoast = boast;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }

    @Override
    public String toString() {
        return "Feed{" +
                "id=" + id +
                ", userId=" + userId +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                ", likeCnt=" + likeCnt +
                ", isBoast=" + isBoast +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
