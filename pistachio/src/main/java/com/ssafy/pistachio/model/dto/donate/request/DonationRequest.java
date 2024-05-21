package com.ssafy.pistachio.model.dto.donate.request;

public class DonationRequest {
    private Long projectId;
    private Long userId;
    private Long amount;

    public DonationRequest(Long projectId,
                           Long userId,
                           Long amount) {
        this.projectId = projectId;
        this.userId = userId;
        this.amount = amount;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DonationRequest{" +
                "projectId=" + projectId +
                ", userId=" + userId +
                ", amount=" + amount +
                '}';
    }
}
