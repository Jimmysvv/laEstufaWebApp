package com.laestufa.laEstufa.service.interfaces;

import com.laestufa.laEstufa.model.UserModel;

import java.util.List;
import java.util.Map;

public interface UserModelService {

    boolean checkForExistingUser(final UserModel user);

    boolean createNewUser(final UserModel user);

    Map getJwtUserDetail(final UserModel user);

    boolean setDbUserSession(final String userId, final String httpSessionId, final String token);

    boolean verifyUser(final UserModel user);

    boolean deleteUserSession(final String id, final String token);

    Map findUserByLogin(final String login);

    boolean checkUserLoginStatus(final String id, final String token, final String userId);

    boolean updateUserProfile(final String userId, final String column, final String value);

    String changeUpperCamelCase(final String changeVar);

    Map getPersonalUserDetail(final String login);

    List<Map> getSearchResult(final String user);

    List<Map> getSearchTagResult(final String tag);
}
