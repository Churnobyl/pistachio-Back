package com.ssafy.pistachio.model.dto.feed.request;

import java.util.Map;

public class ChangeLikesRequest {
    private Map<Long, Boolean> likes;

    public ChangeLikesRequest(Map<Long, Boolean> likes) {
        this.likes = likes;
    }

    public Map<Long, Boolean> getLikes() {
        return likes;
    }

    public void setLikes(Map<Long, Boolean> likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "ChangeLikesRequest{" +
                "likes=" + likes +
                '}';
    }
}
