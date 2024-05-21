package com.ssafy.pistachio.model.dto.comment.response;

import java.util.Objects;

public class CommentResponse {
    private Long id;
    private Long feedId;
    private String content;
    private Long userId;
    private String username;
    private String userProfile;

    public CommentResponse(Long id,
                           Long feedId,
                           String content,
                           Long userId,
                           String username,
                           String userProfile) {
        this.id = id;
        this.feedId = feedId;
        this.content = content;
        this.userId = userId;
        this.username = username;
        this.userProfile = userProfile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    @Override
    public String toString() {
        return "CommentResponse{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", content='" + content + '\'' +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", userProfile='" + userProfile + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentResponse that = (CommentResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(feedId, that.feedId) &&
                Objects.equals(content, that.content) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(username, that.username) &&
                Objects.equals(userProfile, that.userProfile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedId, content, userId, username, userProfile);
    }
}
