package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;

import java.util.List;

public interface UserDao {

    // 유저 회원가입
    public int createUser(User user);

    // 유저 로그인
    public int loginUser(User user);

    // 유저 조회
    public User getUserByUserId(Long id);

    // 유저 수정
    public int updateUser(User user);

    // 유저 검색
    public List<User> searchUser(SearchCondition searchCondition);
}
