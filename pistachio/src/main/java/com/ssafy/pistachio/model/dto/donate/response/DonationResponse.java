package com.ssafy.pistachio.model.dto.donate.response;

public class DonationResponse {
    private Long id;
    private String agencyName;
    private String agencyProfile;
    private Long projectId;
    private String projectName;
    private String projectDescription;
    private Long userId;
    private Long amount;
    private boolean isBoast;

    public DonationResponse() {
    }

    public DonationResponse(Long id,
                            String agencyName,
                            String agencyProfile,
                            Long projectId,
                            String projectName,
                            String projectDescription,
                            Long userId,
                            Long amount,
                            boolean isBoast) {
        this.id = id;
        this.agencyName = agencyName;
        this.agencyProfile = agencyProfile;
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
    }

    public String getAgencyProfile() {
        return agencyProfile;
    }

    public void setAgencyProfile(String agencyProfile) {
        this.agencyProfile = agencyProfile;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
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

    public boolean isBoast() {
        return isBoast;
    }

    public void setBoast(boolean boast) {
        isBoast = boast;
    }

    @Override
    public String toString() {
        return "DonationResponse{" +
                "id=" + id +
                ", agencyName='" + agencyName + '\'' +
                ", agencyProfile='" + agencyProfile + '\'' +
                ", projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                ", projectDescription='" + projectDescription + '\'' +
                ", userId=" + userId +
                ", amount=" + amount +
                ", isBoast=" + isBoast +
                '}';
    }
}
