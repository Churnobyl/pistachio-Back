package com.ssafy.pistachio.filter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.pistachio.authentication.CustomAuthenticationToken;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter() {
        super(new AntPathRequestMatcher("/api/user/login"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (request.getMethod().equals("OPTIONS")) {
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
            response.setHeader("Access-Control-Allow-Headers", "*");
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setStatus(HttpServletResponse.SC_OK);
            return null;
        }

        // 해당 요청이 POST 인지 확인
        if(!isPost(request)) {
            throw new IllegalStateException("Authentication is not supported");
        }

        // POST 이면 body 를 AccountDto( 로그인 정보 DTO ) 에 매핑
        AccountDto accountDto = objectMapper.readValue(request.getReader(), AccountDto.class);

        // ID, PASSWORD 가 있는지 확인
        if(!StringUtils.hasLength(accountDto.getEmail())
                || !StringUtils.hasLength(accountDto.getPassword())) {
            throw new IllegalArgumentException("username or password is empty");
        }

        // 처음에는 인증 되지 않은 토큰 생성
        CustomAuthenticationToken token = new CustomAuthenticationToken(
                accountDto.getEmail(),
                accountDto.getPassword()
        );

        // Manager 에게 인증 처리
        Authentication authenticate = getAuthenticationManager().authenticate(token);

        return authenticate;
    }

    private boolean isPost(HttpServletRequest request) {

        if("POST".equals(request.getMethod())) {
            return true;
        }

        return false;
    }

    public static class AccountDto {
        private String email;
        private String password;

        public AccountDto() {};

        public AccountDto(String email, String password) {
            this.email = email;
            this.password = password;
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
    }
}
