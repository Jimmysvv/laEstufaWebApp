package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.UserPostsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPostsModelRepository extends JpaRepository<UserPostsModel,Long> {

}
