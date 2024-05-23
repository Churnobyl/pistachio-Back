package com.ssafy.pistachio.model.dto.feed.response;

public class InterestResponse {
    private Long projectId;
    private String projectName;

    public InterestResponse() {
    }

    public InterestResponse(Long projectId,
                            String projectName) {
        this.projectId = projectId;
        this.projectName = projectName;
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

    @Override
    public String toString() {
        return "InterestResponse{" +
                "projectId=" + projectId +
                ", projectName='" + projectName + '\'' +
                '}';
    }
}
