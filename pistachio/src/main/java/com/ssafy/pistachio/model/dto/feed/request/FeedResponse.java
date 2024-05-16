package com.ssafy.pistachio.model.dto.feed.request;

import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;

import java.util.ArrayList;
import java.util.List;

public class FeedResponse {

    private final Feed feed;
    private List<FeedPicture> feedPictures;
    private List<FeedComment> feedComment;

    public FeedResponse(Feed feed,
                        List<FeedPicture> feedPictures,
                        List<FeedComment> feedComment) {
        this.feed = feed;
        this.feedPictures = feedPictures;
        this.feedComment = feedComment;
    }

    public static class Builder {
        private Feed feed;
        private List<FeedPicture> feedPictures;
        private List<FeedComment> feedComment;

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

        public FeedResponse build() {
            return new FeedResponse(
                    this.feed,
                    this.feedPictures,
                    this.feedComment);
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

    @Override
    public String toString() {
        return "FeedResponse{" +
                "feed=" + feed +
                ", feedPictures=" + feedPictures +
                ", feedComment=" + feedComment +
                '}';
    }
}
