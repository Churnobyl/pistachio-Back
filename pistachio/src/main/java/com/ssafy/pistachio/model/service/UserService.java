package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;

import java.util.List;

public interface UserService {

    // 유저 회원가입
    public int signup(User user);

    // 유저 로그인
    public int login(User user);

    // 유저 조회
    public User getUser(Long id);

    // 유저 수정
    public int modifyUser(User user);

    // 유저 탈퇴
    public int inactivate(User user);

    // 유저 검색
    public List<User> searchUserBySearchCondition(SearchCondition searchCondition);
}
