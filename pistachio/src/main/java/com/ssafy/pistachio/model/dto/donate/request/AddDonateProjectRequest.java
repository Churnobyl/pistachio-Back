package com.ssafy.pistachio.model.dto.donate.request;

import java.util.Date;

public class AddDonateProjectRequest {
    private Long agencyId;
    private String name;
    private String description;
    private Long targetDonationAmount;
    private Date startTime;
    private Date endTime;

    public AddDonateProjectRequest(Long agencyId,
                                   String name,
                                   String description,
                                   Long targetDonationAmount,
                                   Date startTime,
                                   Date endTime) {
        this.agencyId = agencyId;
        this.name = name;
        this.description = description;
        this.targetDonationAmount = targetDonationAmount;
        this.startTime = startTime;
        this.endTime = endTime;
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
        return "AddDonateProjectRequest{" +
                "agencyId=" + agencyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", targetDonationAmount=" + targetDonationAmount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
