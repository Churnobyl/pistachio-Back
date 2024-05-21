package com.ssafy.pistachio.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FeedLikeDao {

    void insertLike(@Param("feedId") Long feedId, @Param("userId") Long userId);
    void deleteLike(@Param("feedId") Long feedId, @Param("userId") Long userId);

    @Select("SELECT feed_id FROM feed_like WHERE like_user_no = #{userId}")
    List<Long> getLikedFeedIdsByUserId(@Param("userId") Long userId);

    void addLikeCount(@Param("feedId") Long feedId);
    void subLikeCount(@Param("feedId") Long feedId);
}
