package com.ssafy.pistachio.model.dto.user.request;

import org.springframework.security.crypto.password.PasswordEncoder;

public class UserLoginRequest {
    private String email;
    private String password;

    public UserLoginRequest(String email,
                            String password) {
        this.email = email;
        this.password = password;
    }

    public boolean isPasswordMatch(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
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

    @Override
    public String toString() {
        return "UserLoginRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
