package com.ssafy.pistachio.model.dto.donate.response;

import java.util.Date;

public class DonateProjectResponse {
    private Long id;
    private Long agencyId;
    private String agencyName;
    private String name;
    private String description;
    private Long participants;
    private String image;
    private Long currentDonationAmount;
    private Long targetDonationAmount;
    private Date startTime;
    private Date endTime;
    private boolean isEnd;

    public DonateProjectResponse() {
    }

    public DonateProjectResponse(Long id,
                                 Long agencyId,
                                 String agencyName,
                                 String name,
                                 String description,
                                 Long participants,
                                 String image,
                                 Long currentDonationAmount,
                                 Long targetDonationAmount,
                                 Date startTime,
                                 Date endTime,
                                 boolean isEnd) {
        this.id = id;
        this.agencyId = agencyId;
        this.agencyName = agencyName;
        this.name = name;
        this.description = description;
        this.participants = participants;
        this.image = image;
        this.currentDonationAmount = currentDonationAmount;
        this.targetDonationAmount = targetDonationAmount;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isEnd = isEnd;
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

    public String getAgencyName() {
        return agencyName;
    }

    public void setAgencyName(String agencyName) {
        this.agencyName = agencyName;
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

    public Long getParticipants() {
        return participants;
    }

    public void setParticipants(Long participants) {
        this.participants = participants;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public boolean isEnd() {
        return isEnd;
    }

    public void setEnd(boolean end) {
        isEnd = end;
    }

    @Override
    public String toString() {
        return "DonateProjectResponse{" +
                "id=" + id +
                ", agencyId=" + agencyId +
                ", agencyName='" + agencyName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", participants=" + participants +
                ", image='" + image + '\'' +
                ", currentDonationAmount=" + currentDonationAmount +
                ", targetDonationAmount=" + targetDonationAmount +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", isEnd=" + isEnd +
                '}';
    }
}
