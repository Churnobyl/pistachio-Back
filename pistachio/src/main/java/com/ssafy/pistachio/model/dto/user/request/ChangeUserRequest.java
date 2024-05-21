package com.ssafy.pistachio.model.dto.user.request;

public class ChangeUserRequest {
    private long id;
    private long membershipId;
    private String email;
    private String name;
    private String userProfile;

    public ChangeUserRequest(long id,
                             long membershipId,
                             String email,
                             String name,
                             String userProfile) {
        this.id = id;
        this.membershipId = membershipId;
        this.email = email;
        this.name = name;
        this.userProfile = userProfile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(long membershipId) {
        this.membershipId = membershipId;
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

    @Override
    public String toString() {
        return "NormalUserRequest{" +
                "id=" + id +
                ", membershipId=" + membershipId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userProfile='" + userProfile + '\'' +
                '}';
    }
}
