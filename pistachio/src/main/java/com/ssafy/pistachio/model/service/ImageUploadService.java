package com.ssafy.pistachio.model.service;

import com.amazonaws.services.s3.AmazonS3Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ImageUploadService {

    @Value("${cloud.aws.s3.bucket")
    private String bucket;

    @Value("${cloud.aws.s3.bucket.url")
    private String bucketUrl;

    private final AmazonS3Client amazonS3Client;

    @Autowired
    public ImageUploadService(AmazonS3Client amazonS3Client) {
        this.amazonS3Client = amazonS3Client;
    }


}
