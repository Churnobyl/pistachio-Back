package com.ssafy.pistachio.model.dto.user.request;

public class ChangePasswordRequest {
    private long userId;
    private String newPassword;
    private String confirmPassword;

    public ChangePasswordRequest(long userId,
                                 String newPassword,
                                 String confirmPassword) {
        this.userId = userId;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Override
    public String toString() {
        return "ChangePasswordRequest{" +
                "userId=" + userId +
                ", newPassword='" + newPassword + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
