package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.UserModel;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserModel,Long> {

}
