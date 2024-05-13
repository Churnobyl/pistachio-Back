package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int signup(User user) {
        return userDao.createUser(user);
    }

    @Override
    public int login(User user) {
        return userDao.loginUser(user);
    }

    @Override
    public User getUser(Long id) {
        return userDao.getUserByUserId(id);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int inactivate(User user) {
        user.setActivate(false);
        return userDao.updateUser(user);
    }

    @Override
    public List<User> searchUserBySearchCondition(SearchCondition searchCondition) {
        return userDao.searchUser(searchCondition);
    }
}
