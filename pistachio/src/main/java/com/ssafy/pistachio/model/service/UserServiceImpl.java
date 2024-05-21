package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.mail.EmailRequestDto;
import com.ssafy.pistachio.mail.RedisUtil;
import com.ssafy.pistachio.model.dao.UserDao;
import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.dto.user.request.AddUserRequest;
import com.ssafy.pistachio.model.dto.user.request.UserLoginRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final RedisUtil redisUtil;

    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender javaMailSender;

    private final long USER_ROLE = 1; // 1 : 일반 유저로 회원가입

    public UserServiceImpl(UserDao userDao,
                           RedisUtil redisUtil,
                           PasswordEncoder passwordEncoder,
                           JavaMailSender javaMailSender) {
        this.userDao = userDao;
        this.redisUtil = redisUtil;
        this.passwordEncoder = passwordEncoder;
        this.javaMailSender = javaMailSender;
    }

    @Transactional
    @Override
    public int signup(AddUserRequest addUserRequest) {
        String encodePassword = passwordEncoder.encode(addUserRequest.getPassword());
        addUserRequest.setPassword(encodePassword);

        if (userDao.getUserByEmail(addUserRequest.getEmail()) != null) {
            return 0;
        }

        userDao.createUser(addUserRequest);
        userDao.setRole(addUserRequest.getId(), USER_ROLE);

        return 1;
    }

    @Override
    public boolean checkNameDuplicate(String name) {
        return userDao.nameDuplicate(name) == 0;
    }


    @Override
    @Transactional
    public boolean checkEmailDuplicate(String email) {
        return userDao.emailDuplicate(email) == 0;
    }

    @Override
    public void authEmail(EmailRequestDto emailRequestDto) {

        Random random = new Random();
        String authKey = String.valueOf(random.nextInt(888888) + 111111);

        sendAuthEmail(emailRequestDto.getEmail(),
                authKey);
    }

    @Override
    public List<User> search(SearchCondition condition) {
        return userDao.searchUsers(condition);
    }

    @Transactional(readOnly = true)
    public int login(HttpSession session,
                     UserLoginRequest userloginRequest) {

        if (!userloginRequest.isPasswordMatch(passwordEncoder, userloginRequest.getPassword())) {
            throw new UsernameNotFoundException("");
        }

        session.setAttribute("Login_User", userloginRequest.getEmail());

        return userDao.loginUser(userloginRequest);
    }

    @Override
    public User getUserByName(String name) {
        return userDao.getUserByName(name);
    }

    @Override
    public User getUserByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        if (user != null) {
            List<String> roles = userDao.findRolesByUserId(user.getId());
            user.setRoles(roles);
        }
        return user;
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

    private void sendAuthEmail(String email, String authKey) {
        String subject = "Test";
        String text = "회원 가입을 위한 인증번호는 " + authKey + "입니다.";

        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "utf-8");
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(text, true);//포함된 텍스트가 HTML이라는 의미로 true.
            javaMailSender.send(mimeMessage);

        } catch (MessagingException e) {
            e.printStackTrace();
        }

        // 유효 시간(5분)동안 {email, authKey} 저장
        redisUtil.setDataExpire(authKey, email, 60 * 5L);
    }
}
