package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public int signup(User user) {
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        return userDao.createUser(user);
    }

    @Override
    public boolean checkNameDuplicate(String name) {
        return userDao.nameDuplicate(name) == 0;
    }

    @Override
    public boolean checkEmailDuplicate(String email) {
        return userDao.emailDuplicate(email) == 0;
    }

    @Override
    public List<User> search(SearchCondition condition) {
        return userDao.searchUsers(condition);
    }

    @Transactional(readOnly = true)
    public int login(HttpSession session, User user) {

        if (!user.isPasswordMatch(passwordEncoder, user.getPassword())) {
            throw new UsernameNotFoundException("");
        }

        session.setAttribute("Login_User", user.getEmail());

        return userDao.loginUser(user);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public int inactivate(User user) {
        return userDao.inactivateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userDao.getUserByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException("email not found");
        }

        if (!user.isActivate()) {
            throw new UsernameNotFoundException("user is not activated");
        }

        return user;
    }
}
