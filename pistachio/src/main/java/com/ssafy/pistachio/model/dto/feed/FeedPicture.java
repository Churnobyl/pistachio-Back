package com.ssafy.pistachio.model.dto.feed;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedPicture that = (FeedPicture) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(feedId, that.feedId) &&
                Objects.equals(url, that.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, feedId, url);
    }
}
