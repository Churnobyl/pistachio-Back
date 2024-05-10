package com.ssafy.pistachio.model.dto.feed;

import java.util.Date;

/**
 * 피드
 */
public class Feed {
    private Long id;
    private Long userId;
    private Long locationId;
    private Long projectId;
    private String content;
    private Long likeCnt;
    private Date createdTime;
    private Date updatedTime;

    public Feed(Long id,
                Long userId,
                Long locationId,
                Long projectId,
                String content,
                Long likeCnt,
                Date createdTime,
                Date updatedTime) {
        this.id = id;
        this.userId = userId;
        this.locationId = locationId;
        this.projectId = projectId;
        this.content = content;
        this.likeCnt = likeCnt;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
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

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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
                ", locationId=" + locationId +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                ", likeCnt=" + likeCnt +
                ", createdTime=" + createdTime +
                ", updatedTime=" + updatedTime +
                '}';
    }
}
