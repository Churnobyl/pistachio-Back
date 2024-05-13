package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<?> signupUser(@RequestBody User user) {
        Map<String, String> returnObj = new ConcurrentHashMap<>();

        int result = userService.signup(user);

        if (result == 0) {
            returnObj.put("msg", "잘못된 접근입니다.");
            return new ResponseEntity<>(returnObj, HttpStatus.BAD_REQUEST);
        }

        returnObj.put("msg", "가입 완료");
        return new ResponseEntity<>(returnObj, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        System.out.println("user = " + user);
        Map<String, String> returnObj = new ConcurrentHashMap<>();

        int result = userService.login(user);

        if (result == 0) {
            returnObj.put("msg", "잘못된 접근입니다.");
            return new ResponseEntity<>(returnObj, HttpStatus.BAD_REQUEST);
        }

        returnObj.put("msg", "가입 완료");
        return new ResponseEntity<>(returnObj, HttpStatus.OK);
    }
}
