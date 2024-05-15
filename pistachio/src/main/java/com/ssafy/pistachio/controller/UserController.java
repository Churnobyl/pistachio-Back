package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<?> signupUser(@RequestBody @Valid User user) {
        int result = userService.signup(user);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/validate/emails/{email}/exists")
    public ResponseEntity<Boolean> isEmailExist(@PathVariable String email) {
        return ResponseEntity.ok(userService.checkEmailDuplicate(email));
    }

    @GetMapping("/validate/names/{name}/exists")
    public ResponseEntity<Boolean> isNameExist(@PathVariable String name) {
        return ResponseEntity.ok(userService.checkNameDuplicate(name));
    }

    /* 추후 다시 확인 - 검색 로직 */
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam SearchCondition condition) {
        List<User> userList = userService.search(condition);
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(HttpSession session, @RequestBody User user) {
        int result = userService.login(session, user);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("")
    public ResponseEntity<?> updateUser(HttpSession session, @RequestBody User user) {
         String email = (String) session.getAttribute("Login_User");

         User dbUser = userService.getUserByEmail(email);

         user.setId(dbUser.getId());
         userService.modifyUser(user);

         return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteUser(HttpSession session, @RequestBody User user) {
        String email = (String) session.getAttribute("Login_User");

        User dbUser = userService.getUserByEmail(email);

        System.out.println("dbUser = " + dbUser);

        userService.inactivate(dbUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
