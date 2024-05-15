package com.ssafy.pistachio.model.dto.feed.request;

import java.util.List;

public class FeedRequest {
    private Long userId;
    private Long projectId;
    private String content;
    private String locationName;
    private float latitude;
    private float longitude;
    private List<FeedPictureRequest> pictures;
    private List<String> hashtags;

    public FeedRequest(Long userId,
                       Long projectId,
                       String content,
                       String locationName,
                       float latitude,
                       float longitude,
                       List<FeedPictureRequest> pictures,
                       List<String> hashtags) {
        this.userId = userId;
        this.projectId = projectId;
        this.content = content;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.pictures = pictures;
        this.hashtags = hashtags;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public List<FeedPictureRequest> getPictures() {
        return pictures;
    }

    public void setPictures(List<FeedPictureRequest> pictures) {
        this.pictures = pictures;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    @Override
    public String toString() {
        return "FeedRequest{" +
                "userId=" + userId +
                ", projectId=" + projectId +
                ", content='" + content + '\'' +
                ", locationName='" + locationName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", pictures=" + pictures +
                ", hashtags=" + hashtags +
                '}';
    }
}
