package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.FollowModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface FollowRepository extends JpaRepository<FollowModel, Long> {

    @Query(value = "CALL getUserFollowers(:userId)", nativeQuery = true)
    List<Map> getUserFollowers(@Param("userId") Integer userId);

    @Query(value = "CALL getUserFollowing(:userId)", nativeQuery = true)
    List<Map> getUserFollowing(@Param("userId") Integer userId);

    @Query(value = "CALL follow(:follower, :following)", nativeQuery = true)
    boolean follow(@Param("follower") Integer follower,
                   @Param("following") Integer following);

    @Query(value = "CALL unfollow(:follower, :following)", nativeQuery = true)
    boolean unfollow(@Param("follower") Integer follower,
                     @Param("following") Integer following);
}
