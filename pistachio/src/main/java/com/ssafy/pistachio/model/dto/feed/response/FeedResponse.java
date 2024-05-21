package com.ssafy.pistachio.model.dto.feed.response;

import com.ssafy.pistachio.model.dto.comment.FeedComment;
import com.ssafy.pistachio.model.dto.comment.response.CommentResponse;
import com.ssafy.pistachio.model.dto.feed.Feed;
import com.ssafy.pistachio.model.dto.feed.FeedPicture;
import com.ssafy.pistachio.model.dto.user.response.UserResponse;

import java.util.List;
import java.util.stream.Collectors;

public class FeedResponse {

    private final Feed feed;
    private List<FeedPicture> feedPictures;
    private List<CommentResponse> commentResponses;
    private UserResponse userResponse;
    private List<String> pictureUrls;

    public FeedResponse(Feed feed,
                        List<FeedPicture> feedPictures,
                        List<CommentResponse> commentResponses,
                        UserResponse userResponse) {
        this.feed = feed;
        this.feedPictures = feedPictures;
        this.commentResponses = commentResponses;
        this.userResponse = userResponse;
        this.pictureUrls = feedPictures.stream()
                .map(FeedPicture::getUrl)
                .collect(Collectors.toList());
    }

    public static class Builder {
        private Feed feed;
        private List<FeedPicture> feedPictures;
        private List<CommentResponse> commentResponses;
        private UserResponse userResponse;

        public Builder feed(final Feed feed) {
            this.feed = feed;
            return this;
        }

        public Builder feedPictures(final List<FeedPicture> feedPictures) {
            this.feedPictures = feedPictures;
            return this;
        }

        public Builder feedComment(final List<CommentResponse> commentResponse) {
            this.commentResponses = commentResponse;
            return this;
        }

        public Builder userResponse(final UserResponse userResponse) {
            this.userResponse = userResponse;
            return this;
        }

        public FeedResponse build() {
            return new FeedResponse(
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

    public List<String> getPictureUrls() { // 새 getter 추가
        return pictureUrls;
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
                ", pictureUrls=" + pictureUrls +
                ", userResponse=" + userResponse +
                '}';
    }
}