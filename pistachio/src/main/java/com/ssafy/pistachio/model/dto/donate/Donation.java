package com.ssafy.pistachio.model.dto.donate;

/**
 * 기부
 */
public class Donation {
    private Long id;
    private Long projectId;
    private Long userId;
    private Long amount;
    private boolean isBoast;

    public Donation(Long id,
                    Long projectId,
                    Long userId,
                    Long amount,
                    boolean isBoast) {
        this.id = id;
        this.projectId = projectId;
        this.userId = userId;
        this.amount = amount;
        this.isBoast = isBoast;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean getIsBoast() {
        return isBoast;
    }

    public void setIsBoast(boolean isBoast) {
        this.isBoast = isBoast;
    }

    @Override
    public String toString() {
        return "Donation{" +
                "id=" + id +
                ", projectId=" + projectId +
                ", userId=" + userId +
                ", amount=" + amount +
                ", isBoast=" + isBoast +
                '}';
    }
}
