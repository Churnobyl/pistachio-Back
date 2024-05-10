package com.ssafy.pistachio.model.dto.donate;

/**
 * 소속된 프로젝트
 */
public class Affiliated {
    private Long id;
    private Long donatedUserId;
    private Long projectId;

    public Affiliated(Long id,
                      Long donatedUserId,
                      Long projectId) {
        this.id = id;
        this.donatedUserId = donatedUserId;
        this.projectId = projectId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return "Affiliated{" +
                "id=" + id +
                ", donatedUserId=" + donatedUserId +
                ", projectId=" + projectId +
                '}';
    }
}
