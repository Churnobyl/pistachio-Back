package com.ssafy.pistachio.model.dto.feed.request;

public class FeedPictureRequest {
    private int sequenceNumber;
    private String url;

    public FeedPictureRequest(int sequenceNumber, String url) {
        this.sequenceNumber = sequenceNumber;
        this.url = url;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "FeedPictureRequest{" +
                "sequenceNumber=" + sequenceNumber +
                ", url='" + url + '\'' +
                '}';
    }
}
