package com.ssafy.pistachio.model.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AddUserRequest {
    private long id;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\\\W)(?=\\\\S+$).{8,16}\n",
//            message = "비밀번호는 8~16자 영문 대소문자, 숫자, 특수문자를 사용해 주세요.")
    private String password;

    @Pattern(regexp = "^[ㄱ-ㅎ가-힣a-z0-9-_]{2,8}$", message = "닉네임은 특수문자를 제외한 2~8자리여야 합니다.")
    private String name;

    private Long role;

    public AddUserRequest() {
    }

    public AddUserRequest(
                String email,
                String password,
                String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AddUserRequest{" +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
