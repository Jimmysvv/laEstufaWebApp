package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.UserPostsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserPostsModelRepository extends CrudRepository<UserPostsModel,Long> {

}
