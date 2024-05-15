package com.ssafy.pistachio.controller;

import com.ssafy.pistachio.s3.AmazonS3Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class AmazonS3Controller {
    private final AmazonS3Service amazon3SService;

    public AmazonS3Controller(AmazonS3Service amazon3SService) {
        this.amazon3SService = amazon3SService;
    }

    @PostMapping("/uploads")
    public ResponseEntity<Object> uploadFiles(
            @RequestParam(value = "fileType") String fileType,
            @RequestPart(value = "files") List<MultipartFile> multipartFiles) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(amazon3SService.uploadFiles(fileType, multipartFiles));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> deleteFile(
            @RequestParam(value = "uploadFilePath") String uploadFilePath,
            @RequestParam(value = "uuidFileName") String uuidFileName) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(amazon3SService.deleteFile(uploadFilePath, uuidFileName));
    }
}
