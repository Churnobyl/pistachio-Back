package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedLike;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.request.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.request.FeedResponseAll;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.service.FeedService;
import com.ssafy.pistachio.model.service.UserService;
import com.ssafy.pistachio.s3.AmazonS3Service;
import com.ssafy.pistachio.s3.S3FileDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    private final FeedService feedService;
    private final UserService userService;
    private final AmazonS3Service amazonS3Service;

    public FeedController(FeedService feedService, UserService userService, AmazonS3Service amazonS3Service) {
        this.feedService = feedService;
        this.userService = userService;
        this.amazonS3Service = amazonS3Service;
    }

    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> writeFeed(HttpSession session,
                                  @RequestPart(value = "feedRequest") FeedRequest feedRequest,
                                  @RequestPart(value = "pictures") List<MultipartFile> multipartFiles) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);
        feedRequest.setUserId(dbUser.getId());
        List<S3FileDto> pictures = amazonS3Service.uploadFiles(session, "feed", multipartFiles);
        int result = feedService.writeFeed(feedRequest, pictures);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getFeed(HttpSession session) {
        List<FeedResponseAll> responseList = feedService.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<?> getFeed(HttpSession session, @PathVariable("feedId") long feedId) {
        FeedResponse response = feedService.getOne(feedId);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
