package com.ssafy.pistachio.model.dto.feed.request;

import com.ssafy.pistachio.s3.S3FileDto;

public class PictureRequest {
    private Long feedId;
    private String url;

    public PictureRequest(Long feedId, String url) {
        this.feedId = feedId;
        this.url = url;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "PicturesRequest{" +
                "feedId=" + feedId +
                ", url='" + url + '\'' +
                '}';
    }
}
