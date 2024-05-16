package com.ssafy.pistachio.model.dto.feed;

/**
 * 피드 사진
 */
public class FeedPicture {
    Long id;
    Long feedId;
    String url;

    public FeedPicture(Long id,
                       Long feedId,
                       String url) {
        this.id = id;
        this.feedId = feedId;
        this.url = url;
    }

    public FeedPicture(long feedId
            , String url) {
        this.feedId = feedId;
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
                ", url='" + url + '\'' +
                '}';
    }
}
