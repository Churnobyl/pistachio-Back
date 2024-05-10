package com.ssafy.pistachio.model.dto.user;

/**
 * 팔로우
 */
public class Follow {
    private Long id;
    private Long followingId;
    private Long follewedId;

    public Follow(Long id,
                  Long followingId,
                  Long follewedId) {
        this.id = id;
        this.followingId = followingId;
        this.follewedId = follewedId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFollowingId() {
        return followingId;
    }

    public void setFollowingId(Long followingId) {
        this.followingId = followingId;
    }

    public Long getFollewedId() {
        return follewedId;
    }

    public void setFollewedId(Long follewedId) {
        this.follewedId = follewedId;
    }

    @Override
    public String toString() {
        return "Follow{" +
                "id=" + id +
                ", followingId=" + followingId +
                ", follewedId=" + follewedId +
                '}';
    }
}
