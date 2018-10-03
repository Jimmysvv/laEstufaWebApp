package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.JwtUser;
import com.laestufa.laEstufa.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query(value = "CALL getBasicUserDetail(:login)", nativeQuery = true)
    Map findUserByLogin(@Param("login") String login);

    @Query(value = "CALL getJwtUserDetail(:email)", nativeQuery = true)
    Map getJwtUserDetail(@Param("email") String email);

//    Optional<UserModel> findByName(String username);
}
