package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.model.dto.comment.request.AddCommentRequest;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.request.FeedRequest;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponse;
import com.ssafy.pistachio.model.dto.feed.response.FeedResponseAll;
import com.ssafy.pistachio.model.dto.user.User;
import com.ssafy.pistachio.model.service.FeedService;
import com.ssafy.pistachio.model.service.UserService;
import com.ssafy.pistachio.s3.AmazonS3Service;
import com.ssafy.pistachio.s3.S3FileDto;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllFeed(HttpSession session) {
        List<FeedResponseAll> responseList = feedService.getAll();
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @GetMapping("/{feedId}")
    public ResponseEntity<?> getOneFeed(HttpSession session, @PathVariable("feedId") long feedId) {
        FeedResponse response = feedService.getOne(feedId);

        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping("/{feedId}")
    public ResponseEntity<?> modifyFeed(HttpSession session,
                                        @PathVariable("feedId") long feedId,
                                        @RequestPart(value = "feedRequest") FeedRequest feedRequest,
                                        @RequestPart(value = "newPictures", required = false) List<MultipartFile> newMultipartFiles,
                                        @RequestPart(value = "existingPictureUrls", required = false) List<String> existingPictureUrls) {
        FeedResponse dbFeed = feedService.getOne(feedId);

        // 해당 피드 없으면 404
        if (dbFeed == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);

        // 수정하려는 유저가 글 쓴 유저와 다르면 403
        if (dbFeed.getFeed().getUserId() != dbUser.getId()) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        // 기존의 이미지 URL을 가져옴
        List<String> oldPictureUrls = dbFeed.getPictureUrls();

        // 기존 이미지와 새로운 이미지 비교
        List<String> deletePictureUrls = new ArrayList<>(oldPictureUrls);
        if (existingPictureUrls != null) {
            deletePictureUrls.removeAll(existingPictureUrls);
        }

        // 새로운 이미지 URL 목록
        List<String> newPictureUrls = existingPictureUrls != null ? new ArrayList<>(existingPictureUrls) : new ArrayList<>();

        // 새로운 이미지만 업로드
        List<S3FileDto> newPictures = new ArrayList<>();
        if (newMultipartFiles != null && !newMultipartFiles.isEmpty()) {
            newPictures = amazonS3Service.uploadFiles(session, "feed", newMultipartFiles);
            for (S3FileDto newPicture : newPictures) {
                newPictureUrls.add(newPicture.getUploadFilepath() + "/" + newPicture.getUploadFileName());
            }
        }

        // 삭제할 이미지를 S3에서 삭제
        amazonS3Service.deleteFiles(deletePictureUrls);

        // FeedRequest에 새로운 이미지 URL 추가
        feedRequest.setPictureUrls(newPictureUrls);

        // 피드 수정
        feedService.modifyFeed(feedId, feedRequest);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{feedId}")
    public ResponseEntity<?> deleteFeed(HttpSession session, @PathVariable("feedId") Long feedId) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);
        FeedResponse dbFeed = feedService.getOne(feedId);

        if (dbFeed == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (dbFeed.getFeed().getUserId() != dbUser.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        amazonS3Service.deleteFiles(dbFeed.getPictureUrls());
        feedService.deleteFeed(feedId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/{feedId}/comments")
    public ResponseEntity<?> writeComment(HttpSession session, @PathVariable("feedId") Long feedId, @RequestBody AddCommentRequest addCommentRequest) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);
        addCommentRequest.setCommentUserNo(dbUser.getId());
        addCommentRequest.setFeedId(feedId);
        int result = feedService.writeComment(addCommentRequest);

        if (result == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{feedId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(HttpSession session, @PathVariable("feedId") Long feedId, @PathVariable("commentId") Long commentId) {
        String email = (String) session.getAttribute("Login_User");
        User dbUser = userService.getUserByEmail(email);
        CommentResponse commentResponse = feedService.readComment(commentId);

        if (dbUser.getId() != commentResponse.getUserId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        feedService.deleteComment(commentId);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
