package com.ssafy.pistachio.model.dto.feed.request;

import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.user.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FeedResponse {

    private final Feed feed;
    private List<FeedPicture> feedPictures;
    private List<FeedComment> feedComment;
    private User user;
    private List<String> pictureUrls;

    public FeedResponse(Feed feed,
                        List<FeedPicture> feedPictures,
                        List<FeedComment> feedComment,
                        User user) {
        this.feed = feed;
        this.feedPictures = feedPictures;
        this.feedComment = feedComment;
        this.user = user;
        this.pictureUrls = feedPictures.stream()
                .map(FeedPicture::getUrl)
                .collect(Collectors.toList());
    }

    public static class Builder {
        private Feed feed;
        private List<FeedPicture> feedPictures;
        private List<FeedComment> feedComment;
        private User user;

        public Builder feed(final Feed feed) {
            this.feed = feed;
            return this;
        }

        public Builder feedPictures(final List<FeedPicture> feedPictures) {
            this.feedPictures = feedPictures;
            return this;
        }

        public Builder feedComment(final List<FeedComment> feedComment) {
            this.feedComment = feedComment;
            return this;
        }

        public Builder user(final User user) {
            this.user = user;
            return this;
        }

        public FeedResponse build() {
            return new FeedResponse(
                    this.feed,
                    this.feedPictures,
                    this.feedComment,
                    this.user);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Feed getFeed() {
        return feed;
    }

    public List<FeedPicture> getFeedPictures() {
        return feedPictures;
    }

    public List<FeedComment> getFeedComment() {
        return feedComment;
    }

    public List<String> getPictureUrls() { // 새 getter 추가
        return pictureUrls;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "FeedResponse{" +
                "feed=" + feed +
                ", feedPictures=" + feedPictures +
                ", feedComment=" + feedComment +
                ", pictureUrls=" + pictureUrls +
                ", user=" + user +
                '}';
    }
}
