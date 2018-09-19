package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.UserModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<UserModel,Long> {

    @Query(value = "SELECT u.* FROM UserModel u WHERE u.login = :login", nativeQuery = true)
    UserModel findUserByLogin(@Param("login") String login);

}
