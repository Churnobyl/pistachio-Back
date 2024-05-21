package com.ssafy.pistachio.model.dto.donate.request;

public class ChangeAfiiliatedRequest {
    private Long donatedUserId;
    private Long projectId;

    public ChangeAfiiliatedRequest(Long donatedUserId,
                                   Long projectId) {
        this.donatedUserId = donatedUserId;
        this.projectId = projectId;
    }

    public Long getDonatedUserId() {
        return donatedUserId;
    }

    public void setDonatedUserId(Long donatedUserId) {
        this.donatedUserId = donatedUserId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    @Override
    public String toString() {
        return "ChangeAfiiliatedRequest{" +
                "donatedUserId=" + donatedUserId +
                ", projectId=" + projectId +
                '}';
    }
}
