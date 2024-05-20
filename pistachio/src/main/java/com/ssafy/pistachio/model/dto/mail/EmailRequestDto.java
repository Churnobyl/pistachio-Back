package com.ssafy.pistachio.model.dto.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmailRequestDto {

    @Email
    @NotBlank(message = "이메일은 필수입니다.")
    private String email;

    public EmailRequestDto() {
    }

    public EmailRequestDto(String email) {
        this.email = email;
    }

    public @Email @NotBlank(message = "이메일은 필수입니다.") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotBlank(message = "이메일은 필수입니다.") String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmailRequestDto{" +
                "email='" + email + '\'' +
                '}';
    }
}
