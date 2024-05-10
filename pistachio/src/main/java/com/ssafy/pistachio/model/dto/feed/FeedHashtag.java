package com.ssafy.pistachio.model.dto.feed;

/**
 * 피드 해시태그
 */
public class FeedHashtag {
    Long id;
    Long feedId;
    Long hashtagId;

    public FeedHashtag(Long id,
                       Long feedId,
                       Long hashtagId) {
        this.id = id;
        this.feedId = feedId;
        this.hashtagId = hashtagId;
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

    public Long getHashtagId() {
        return hashtagId;
    }

    public void setHashtagId(Long hashtagId) {
        this.hashtagId = hashtagId;
    }

    @Override
    public String toString() {
        return "FeedHashtag{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", hashtagId=" + hashtagId +
                '}';
    }
}
