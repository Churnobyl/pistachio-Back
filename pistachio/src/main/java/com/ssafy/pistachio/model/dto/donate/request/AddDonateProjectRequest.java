package com.ssafy.pistachio.model.dto.donate.request;

import java.util.Date;

public class AddDonateProjectRequest {
    private Long id;
    private Long agencyId;
    private String name;
    private String description;
    private String image;
    private Long targetDonationAmount;
    private Date startTime;
    private Date endTime;

    public AddDonateProjectRequest() {
    }

    public AddDonateProjectRequest(Long id,
                                   Long agencyId,
                                   String name,
                                   String description,
                                   String image,
                                   Long targetDonationAmount,
                                   Date startTime,
                                   Date endTime) {
        this.id = id;
        this.agencyId = agencyId;
        this.name = name;
        this.description = description;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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
                "id=" + id +
                "agencyId=" + agencyId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", targetDonationAmount=" + targetDonationAmount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}
