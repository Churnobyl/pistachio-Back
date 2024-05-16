package com.ssafy.pistachio.model.dto.feed.request;

import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;

import java.util.ArrayList;
import java.util.List;

public class FeedResponseAll {

    private final Feed feed;
    private List<FeedPicture> feedPictures;
    private List<FeedComment> feedComment;

    public FeedResponseAll(Feed feed,
                           List<FeedPicture> feedPictures,
                           List<FeedComment> feedComment) {
        this.feed = feed;
        this.feedPictures = feedPictures;
        this.feedComment = feedComment;
    }

    public static class Builder {
        private Feed feed;
        private List<FeedPicture> feedPictures = new ArrayList<>();
        private List<FeedComment> feedComment = new ArrayList<>();

        public Builder feed(final Feed feed) {
            this.feed = feed;
            return this;
        }

        public Builder feedPictures(final FeedPicture feedPicture) {
            this.feedPictures.add(feedPicture);
            return this;
        }

        public Builder feedComment(final FeedComment feedComment) {
            this.feedComment.add(feedComment);
            return this;
        }

        public FeedResponseAll build() {
            return new FeedResponseAll(
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
