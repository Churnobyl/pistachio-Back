package com.ssafy.pistachio.model.dao;

import com.ssafy.pistachio.model.dto.feed.FeedPicture;

public interface FeedPictureDao {
    void deleteByFeedId(long feedId);

    void insert(FeedPicture feedPicture);
}
