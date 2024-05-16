package com.ssafy.pistachio.s3;

import java.io.Serializable;

public class S3FileDto implements Serializable {
    private final String originalFileName;
    private final String uploadFileName;
    private final String uploadFilepath;
    private final String uploadFileUrl;

    private S3FileDto(String originalFileName,
                     String uploadFileName,
                     String uploadFilepath,
                     String uploadFileUrl) {
        this.originalFileName = originalFileName;
        this.uploadFileName = uploadFileName;
        this.uploadFilepath = uploadFilepath;
        this.uploadFileUrl = uploadFileUrl;
    }

    public static class Builder {

        private String originalFileName;
        private String uploadFileName;
        private String uploadFilepath;
        private String uploadFileUrl;

        public Builder originalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
            return this;
        }

        public Builder uploadFileName(String uploadFileName) {
            this.uploadFileName = uploadFileName;
            return this;
        }

        public Builder uploadFilePath(String uploadFilepath) {
            this.uploadFilepath = uploadFilepath;
            return this;
        }

        public Builder uploadFileUrl(String uploadFileUrl) {
            this.uploadFileUrl = uploadFileUrl;
            return this;
        }

        public S3FileDto build() {
            return new S3FileDto(originalFileName, uploadFileName, uploadFilepath, uploadFileUrl);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public String getUploadFilepath() {
        return uploadFilepath;
    }

    public String getUploadFileUrl() {
        return uploadFileUrl;
    }

    @Override
    public String toString() {
        return "S3FileDto{" +
                "originalFileName='" + originalFileName + '\'' +
                ", uploadFileName='" + uploadFileName + '\'' +
                ", uploadFilepath='" + uploadFilepath + '\'' +
                ", uploadFileUrl='" + uploadFileUrl + '\'' +
                '}';
    }
}
