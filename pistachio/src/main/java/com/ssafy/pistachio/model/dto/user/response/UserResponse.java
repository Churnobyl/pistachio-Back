package com.ssafy.pistachio.model.dto.user.response;

public class UserResponse {
    private long userId;
    private long membershipId;
    private String membershipName;
    private String membershipProfileUrl;
    private String email;
    private String name;
    private String userProfile;
    private boolean isAdmin;
    private long userType;
    private long followingCnt;
    private long followerCnt;

    public UserResponse() {
    }

    public UserResponse(long userId,
                        long membershipId,
                        String membershipName,
                        String membershipProfileUrl,
                        String email,
                        String name,
                        String userProfile,
                        boolean isAdmin,
                        long userType,
                        long followingCnt,
                        long followerCnt) {
        this.userId = userId;
        this.membershipId = membershipId;
        this.membershipName = membershipName;
        this.membershipProfileUrl = membershipProfileUrl;
        this.email = email;
        this.name = name;
        this.userProfile = userProfile;
        this.isAdmin = isAdmin;
        this.userType = userType;
        this.followingCnt = followingCnt;
        this.followerCnt = followerCnt;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
    }

    public String getMembershipName() {
        return membershipName;
    }

    public void setMembershipName(String membershipName) {
        this.membershipName = membershipName;
    }

    public String getMembershipProfileUrl() {
        return membershipProfileUrl;
    }

    public void setMembershipProfileUrl(String membershipProfileUrl) {
        this.membershipProfileUrl = membershipProfileUrl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public long getUserType() {
        return userType;
    }

    public void setUserType(long userType) {
        this.userType = userType;
    }

    public long getFollowingCnt() {
        return followingCnt;
    }

    public void setFollowingCnt(long followingCnt) {
        this.followingCnt = followingCnt;
    }

    public long getFollowerCnt() {
        return followerCnt;
    }

    public void setFollowerCnt(long followerCnt) {
        this.followerCnt = followerCnt;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "userId=" + userId +
                ", membershipId=" + membershipId +
                ", membershipName='" + membershipName + '\'' +
                ", membershipProfileUrl='" + membershipProfileUrl + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", isAdmin=" + isAdmin +
                ", userType=" + userType +
                ", followingCnt=" + followingCnt +
                ", followerCnt=" + followerCnt +
                '}';
    }
}
