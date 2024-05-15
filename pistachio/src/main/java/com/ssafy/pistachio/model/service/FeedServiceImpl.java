package com.ssafy.pistachio.model.service;

import com.ssafy.pistachio.model.dto.feed.Feed;

import java.util.List;

public class FeedServiceImpl implements FeedService {
    @Override
    public int writeFeed(Feed feed) {
        return 0;
    }

    @Override
    public List<Feed> getAll() {
        return List.of();
    }

    @Override
    public Feed getOne(int feedId) {
        return null;
    }

    @Override
    public int modifyFeed(Feed feed) {
        return 0;
    }

    @Override
    public int deleteFeed(int feedId) {
        return 0;
    }
}
