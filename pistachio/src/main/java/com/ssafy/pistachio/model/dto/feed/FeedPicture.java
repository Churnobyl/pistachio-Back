package com.ssafy.pistachio.model.dto.feed;

/**
 * 피드 사진
 */
public class FeedPicture {
    Long id;
    Long feedId;
    Long sequenceNumber;
    String url;

    public FeedPicture(Long id,
                       Long feedId,
                       Long sequenceNumber,
                       String url) {
        this.id = id;
        this.feedId = feedId;
        this.sequenceNumber = sequenceNumber;
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
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
        return "FeedPicture{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", sequenceNumber=" + sequenceNumber +
                ", url='" + url + '\'' +
                '}';
    }
}
