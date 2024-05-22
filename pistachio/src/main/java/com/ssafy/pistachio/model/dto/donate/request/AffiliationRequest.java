package com.ssafy.pistachio.model.dto.donate.request;

public class AffiliationRequest {
    private Long userId;
    private Long projectId;

    public AffiliationRequest() {
    }

    public AffiliationRequest(Long userId, Long projectId) {
        this.userId = userId;
        this.projectId = projectId;
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

    @Override
    public String toString() {
        return "AffiliationRequest{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                '}';
    }
}
