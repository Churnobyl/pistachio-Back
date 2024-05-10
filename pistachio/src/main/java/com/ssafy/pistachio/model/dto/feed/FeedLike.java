package com.ssafy.pistachio.model.dto.feed;

/**
 * 피드 좋아요
 */
public class FeedLike {
    Long id;
    Long feedId;
    Long likeUserNo;

    public FeedLike(Long id,
                    Long feedId,
                    Long likeUserNo) {
        this.id = id;
        this.feedId = feedId;
        this.likeUserNo = likeUserNo;
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

    public Long getLikeUserNo() {
        return likeUserNo;
    }

    public void setLikeUserNo(Long likeUserNo) {
        this.likeUserNo = likeUserNo;
    }

    @Override
    public String toString() {
        return "FeedLike{" +
                "id=" + id +
                ", feedId=" + feedId +
                ", likeUserNo=" + likeUserNo +
                '}';
    }
}
