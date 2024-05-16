package com.ssafy.pistachio.model.dto.feed.request;

import com.ssafy.pistachio.s3.S3FileDto;

import java.util.List;

public class FeedRequest {
    private Long id;
    private Long userId;
    private Long projectId;
    private String content;

    public FeedRequest(Long userId,
                       Long projectId,
                       String content) {
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

    @Override
    public String toString() {
        return "FeedRequest{" +
                "id=" + id +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                '}';
    }
}
