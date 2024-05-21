package com.ssafy.pistachio.model.dto.feed.response;

import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.user.response.UserResponse;

import java.util.ArrayList;
import java.util.List;

public class FeedResponseAll {

    private final Feed feed;
    private List<FeedPicture> feedPictures;
    private List<CommentResponse> commentResponses;
    private UserResponse userResponse;

    public FeedResponseAll(Feed feed,
                           List<FeedPicture> feedPictures,
                           List<CommentResponse> commentResponses,
                           UserResponse userResponse) {
        this.feed = feed;
        this.feedPictures = feedPictures;
        this.commentResponses = commentResponses;
        this.userResponse = userResponse;
    }

    public static class Builder {
        private Feed feed;
        private List<FeedPicture> feedPictures = new ArrayList<>();
        private List<CommentResponse> commentResponses = new ArrayList<>();
        private UserResponse userResponse;

        public Builder feed(final Feed feed) {
            this.feed = feed;
            return this;
        }

        public Builder feedPictures(final FeedPicture feedPicture) {
            this.feedPictures.add(feedPicture);
            return this;
        }

        public Builder commentResponses(final CommentResponse commentResponses) {
            this.commentResponses.add(commentResponses);
            return this;
        }

        public Builder userResponse(final UserResponse userResponse) {
            this.userResponse = userResponse;
            return this;
        }

        public FeedResponseAll build() {
            return new FeedResponseAll(
                    this.feed,
                    this.feedPictures,
                    this.commentResponses,
                    this.userResponse);
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

    public List<CommentResponse> getCommentResponses() {
        return commentResponses;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    @Override
    public String toString() {
        return "FeedResponse{" +
                "feed=" + feed +
                ", feedPictures=" + feedPictures +
                ", commentResponses=" + commentResponses +
                ", userResponse=" + userResponse +
                '}';
    }
}
