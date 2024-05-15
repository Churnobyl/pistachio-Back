package com.ssafy.pistachio.authentication.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.user.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final UserDao userDao;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationSuccessHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        User user = userDao.getUserByEmail(authentication.getPrincipal().toString());

        request.getSession().setAttribute("Login_User", user.getEmail());

        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getWriter(), user);
    }
}
