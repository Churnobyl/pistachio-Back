package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.mail.EmailRequestDto;
import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface UserService {

    // 유저 회원가입
    public int signup(User user);

    // 유저 이름 중복 확인
    public boolean checkNameDuplicate(String name);

    // 유저 이메일 중복 확인
    public boolean checkEmailDuplicate(String email);

    // 유저 검색
    public List<User> search(SearchCondition condition);

    // 유저 로그인
    public int login(HttpSession session, User user);

    public void authEmail(EmailRequestDto emailRequestDto);

    // 유저 이름으로 조회
    public User getUserByName(String name);

    // 유저 이메일로 조회
    public User getUserByEmail(String email);

    // 유저 수정
    public int modifyUser(User user);

    // 유저 탈퇴
    public int inactivate(User user);
}
