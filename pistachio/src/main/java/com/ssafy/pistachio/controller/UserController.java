package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.model.dto.mail.EmailRequestDto;
import com.ssafy.pistachio.model.dto.user.SearchCondition;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.dto.user.request.AddUserRequest;
import com.ssafy.pistachio.model.dto.user.request.UserLoginRequest;
import com.ssafy.pistachio.model.service.UserService;
import com.ssafy.pistachio.s3.AmazonS3Service;
import com.ssafy.pistachio.s3.S3FileDto;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AmazonS3Service amazonS3Service;

    public UserController(UserService userService, AmazonS3Service amazonS3Service) {
        this.userService = userService;
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping("")
    public ResponseEntity<?> signupUser(@RequestBody @Valid AddUserRequest addUserRequest) {
        int result = userService.signup(addUserRequest);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/validate/emails/{email}/exists")
    public ResponseEntity<Boolean> isEmailExist(@PathVariable String email) {
        boolean result = userService.checkEmailDuplicate(email);

        if (!result) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/validate/names/{name}/exists")
    public ResponseEntity<Boolean> isNameExist(@PathVariable String name) {
        return ResponseEntity.ok(userService.checkNameDuplicate(name));
    }

    @PostMapping("/validate/emails")
    public ResponseEntity<Boolean> authEmails(@RequestBody @Valid EmailRequestDto emailRequestDto) {
        userService.authEmail(emailRequestDto);
        return ResponseEntity.ok().build();
    }

    /* 추후 다시 확인 - 검색 로직 */
    @GetMapping("/search")
    public ResponseEntity<List<User>> searchUser(@RequestParam SearchCondition condition) {
        System.out.println("condition = " + condition);
        List<User> userList = userService.search(condition);
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(HttpSession session, @RequestBody UserLoginRequest userLoginRequest) {
        int result = userService.login(session, userLoginRequest);

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

        userService.inactivate(dbUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/profile")
    public ResponseEntity<?> uploadProfile(HttpSession session, @RequestPart(value = "files") List<MultipartFile> multipartFiles) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);
        
        // 유저 로그인 상태 아닐 경우
        if (dbUser == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // 유저 프로필 있을 경우
        if (!(dbUser.getUserProfile() == null || dbUser.getUserProfile().equals(""))) {
            deleteUser(session, dbUser);
        }
        
        List<S3FileDto> uploadFiles = amazonS3Service.uploadFiles(session, "profile", multipartFiles);

        // db에 저장
        try {
            S3FileDto uploadFile = uploadFiles.get(0);
            dbUser.setUserProfile(uploadFile.getUploadFilepath() + "/" + uploadFile.getUploadFileName());
            userService.modifyUser(dbUser);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(uploadFiles);
    }

    @DeleteMapping("/profile")
    public ResponseEntity<?> deleteProfile(HttpSession session) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);
        String profileUrl = dbUser.getUserProfile();

        if (!amazonS3Service.deleteFile(profileUrl).equals("success")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        dbUser.setUserProfile("");
        userService.modifyUser(dbUser);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
