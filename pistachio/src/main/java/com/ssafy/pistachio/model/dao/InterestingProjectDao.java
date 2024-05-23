package com.ssafy.pistachio.model.dao;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface InterestingProjectDao {
    @Select("SELECT count FROM interesting_project WHERE user_id = #{userId} AND project_id = #{projectId}")
    Long getCount(@Param("userId") Long userId, @Param("projectId") Long projectId);

    @Insert("INSERT INTO interesting_project (user_id, project_id, count) VALUES (#{userId}, #{projectId}, #{count})")
    void insertInterestingProject(@Param("userId") Long userId, @Param("projectId") Long projectId, @Param("count") Long count);

    @Update("UPDATE interesting_project SET count = count + #{increment} WHERE user_id = #{userId} AND project_id = #{projectId}")
    void updateCount(@Param("userId") Long userId, @Param("projectId") Long projectId, @Param("increment") Long increment);

    @Delete("DELETE FROM interesting_project WHERE user_id = #{userId} AND project_id = #{projectId}")
    void deleteInterestingProject(@Param("userId") Long userId, @Param("projectId") Long projectId);

    @Select("SELECT project_id FROM interesting_project WHERE user_id = #{userId} AND count > 0")
    List<Long> getLikedProjectIdsByUserId(@Param("userId") Long userId);
}
