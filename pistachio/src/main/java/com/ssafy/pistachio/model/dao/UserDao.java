package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;

import java.util.List;

public interface UserDao {

    // 유저 회원가입
    public int createUser(User user);

    // 유저 이름 중복 확인
    public int nameDuplicate(String username);

    // 유저 이메일 중복 확인
    public int emailDuplicate(String email);

    // 유저 검색
    public List<User> searchUsers(SearchCondition condition);

    // 유저 로그인
    public int loginUser(User user);

    // 유저 상세 조회
    public User getUserByName(String name);

    // 이메일로 유저 조회
    public User getUserByEmail(String email);

    // 유저 수정
    public int updateUser(User user);

    // 유저 탈퇴
    public int inactivateUser(User user);

    /* 프로필 사진 */

    // 프로필 이미지 세팅
    public int setProfileImage(User user);

    // 관리자 인증
    public int setAdmin(User user);

    /* 소속된 프로젝트 */
    public int setAffiliated(User user);
}
