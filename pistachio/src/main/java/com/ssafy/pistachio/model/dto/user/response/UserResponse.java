package com.ssafy.pistachio.model.dto.user.response;

public class UserResponse {
    private long id;
    private long membershipId;
    private String email;
    private String name;
    private String userProfile;
    private boolean isAdmin;

    public UserResponse() {
    }

    public UserResponse(long id,
                        long membershipId,
                        String email,
                        String name,
                        String userProfile,
                        boolean isAdmin) {
        this.id = id;
        this.membershipId = membershipId;
        this.email = email;
        this.name = name;
        this.userProfile = userProfile;
        this.isAdmin = isAdmin;
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

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", membershipId=" + membershipId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
