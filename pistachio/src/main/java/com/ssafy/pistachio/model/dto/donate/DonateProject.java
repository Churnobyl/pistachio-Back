package com.ssafy.pistachio.model.dto.donate;

import java.util.Date;

/**
 * 기부 프로젝트
 */
public class DonateProject {
    private Long id;
    private Long agencyId;
    private String name;
    private String description;
    private Long currentDonationAmount;
    private Long targetDonationAmount;
    private Date startTime;
    private Date endTime;

    public DonateProject(Long id,
                         Long agencyId,
                         String name,
                         String description,
                         Long currentDonationAmount,
                         Long targetDonationAmount,
                         Date startTime,
                         Date endTime) {
        this.id = id;
        this.agencyId = agencyId;
        this.name = name;
        this.description = description;
        this.currentDonationAmount = currentDonationAmount;
        this.targetDonationAmount = targetDonationAmount;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(Long agencyId) {
        this.agencyId = agencyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCurrentDonationAmount() {
        return currentDonationAmount;
    }

    public void setCurrentDonationAmount(Long currentDonationAmount) {
        this.currentDonationAmount = currentDonationAmount;
    }

    public Long getTargetDonationAmount() {
        return targetDonationAmount;
    }

    public void setTargetDonationAmount(Long targetDonationAmount) {
        this.targetDonationAmount = targetDonationAmount;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "DonateProject{" +
                "id=" + id +
                ", agencyId=" + agencyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", currentDonationAmount=" + currentDonationAmount +
                ", targetDonationAmount=" + targetDonationAmount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
