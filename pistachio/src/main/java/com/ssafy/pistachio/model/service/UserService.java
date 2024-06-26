package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.mail.EmailRequestDto;
import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.dto.user.request.AddUserRequest;
import com.ssafy.pistachio.model.dto.user.request.UserLoginRequest;
import com.ssafy.pistachio.model.dto.user.response.UserResponse;
import jakarta.servlet.http.HttpSession;

import java.util.List;
import java.util.Map;

public interface UserService {

    // 유저 회원가입
    public int signup(AddUserRequest addUserRequest);

    // 유저 이름 중복 확인
    public boolean checkNameDuplicate(String name);

    // 유저 이메일 중복 확인
    public boolean checkEmailDuplicate(String email);

    // 유저 검색
    public List<User> search(SearchCondition condition);

    // 유저 로그인
    public int login(HttpSession session, UserLoginRequest userLoginRequest);

    public void authEmail(EmailRequestDto emailRequestDto);

    // 유저 이메일로 조회
    public User getUserByEmail(String email);

    // 유저 아이디로 조회
    public UserResponse getUserByIdForResponse(long id);

    // 유저 수정
    public int modifyUser(User user);

    // 유저 탈퇴
    public int inactivate(User user);

    // 팔로우 업데이트
    void batchUpdateFollow(Long userId, Map<Long, Boolean> followStatusMap);
}
