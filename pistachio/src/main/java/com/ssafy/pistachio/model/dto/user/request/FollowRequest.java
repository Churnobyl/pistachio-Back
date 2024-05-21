package com.ssafy.pistachio.model.dto.user.request;

public class FollowRequest {
    private Long followingId;
    private Long followedId;

    public FollowRequest(Long followingId,
                         Long followedId) {
        this.followingId = followingId;
        this.followedId = followedId;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public Long getFollowedId() {
        return followedId;
    }

    public void setFollowedId(Long followedId) {
        this.followedId = followedId;
    }

    @Override
    public String toString() {
        return "FollowRequest{" +
                "followingId=" + followingId +
                ", followedId=" + followedId +
                '}';
    }
}
