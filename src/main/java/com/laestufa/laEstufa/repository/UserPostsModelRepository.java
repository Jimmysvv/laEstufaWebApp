package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.UserPostsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserPostsModelRepository extends JpaRepository<UserPostsModel,Long> {

    @Query(value = "CALL findAllPosts()", nativeQuery = true)
    List<Map> findAllPosts();

    @Query(value = "CALL findAllUserPosts(:login)", nativeQuery = true)
    List<Map> findAllUserPosts(@Param("login") String login);
}
