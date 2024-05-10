package com.ssafy.pistachio.model.dto.user;

/**
 * 유저
 */
public class User {
    private Long id;
    private Long membership_id;
    private String email;
    private String password;
    private String name;
    private String userProfile;
    private boolean isAdmin;
    private boolean isActivate;

    public User(Long id,
                Long membership_id,
                String email,
                String password,
                String name,
                String userProfile,
                boolean isAdmin,
                boolean isActivate) {
        this.id = id;
        this.membership_id = membership_id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.userProfile = userProfile;
        this.isAdmin = isAdmin;
        this.isActivate = isActivate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMembership_id() {
        return membership_id;
    }

    public void setMembership_id(Long membership_id) {
        this.membership_id = membership_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isActivate() {
        return isActivate;
    }

    public void setActivate(boolean activate) {
        isActivate = activate;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", membership_id=" + membership_id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", userProfile='" + userProfile + '\'' +
                ", isAdmin=" + isAdmin +
                ", isActivate=" + isActivate +
                '}';
    }
}
