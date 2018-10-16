package com.laestufa.laEstufa.repository;

import com.laestufa.laEstufa.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Map;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {

    @Query(value = "CALL getBasicUserDetail(:login)", nativeQuery = true)
    Map findUserByLogin(@Param("login") String login);

    @Query(value = "CALL getJwtUserDetail(:email)", nativeQuery = true)
    Map getJwtUserDetail(@Param("email") String email);

    @Query(value = "CALL checkForExistingUser(:email, :login)", nativeQuery = true)
    boolean checkForExistingUser(@Param("email") String email,
                                 @Param("login") String login);

    @Query(value = "CALL setDbUserSession(:userId, :httpSessionId, :token)", nativeQuery = true)
    boolean setDbUserSession(@Param("userId") String userId,
                             @Param("httpSessionId") String httpSessionId,
                             @Param("token") String token);

    @Query(value = "CALL verifyUser(:email, :password)", nativeQuery = true)
    boolean verifyUser(@Param("email") String userEmail,
                       @Param("password") String userPass);

    @Query(value = "CALL deleteUserSession(:id, :token)", nativeQuery = true)
    boolean deleteUserSession(@Param("id") String id,
                              @Param("token") String token);

    @Query(value = "CALL checkUserLoginStatus(:id, :token, :userId)", nativeQuery = true)
    boolean checkUserLoginStatus(@Param("id") String id,
                                 @Param("token") String token,
                                 @Param("userId") String userId);

//    Optional<UserModel> findByName(String username);
}
