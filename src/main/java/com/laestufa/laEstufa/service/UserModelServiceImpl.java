package com.laestufa.laEstufa.service;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserModelServiceImpl implements UserModelService {

    @Autowired
    private UserRepository userRepository;

    protected final Logger log = Logger.getLogger(getClass());


    @Override
    public boolean checkForExistingUser(final UserModel user) {
        return userRepository.checkForExistingUser(user.getEmail(), user.getLogin());
    }

    @Override
    public boolean createNewUser(final UserModel user) {
        try {
            userRepository.save(user);
            log.info("Successfully registered new user");
            return true;
        } catch (RuntimeException e) {
            log.warning("There was a problem while tyring to save user: " + user.getEmail());
            log.warning("With error: ", e);
            return false;
        }
    }

    @Override
    public Map getJwtUserDetail(UserModel user) {
        return userRepository.getJwtUserDetail(user.getEmail());
    }

    @Override
    public boolean setDbUserSession(String userId, String httpSessionId, String token) {
        return userRepository.setDbUserSession(userId, httpSessionId, token);
    }

    @Override
    public boolean verifyUser(UserModel user) {
        String userEmail = user.getEmail();
        String userPass = user.getPassword();
        return userRepository.verifyUser(userEmail, userPass);
    }

    @Override
    public boolean deleteUserSession(String id, String token) {
        return userRepository.deleteUserSession(id, token);
    }

    @Override
    public Map findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public boolean checkUserLoginStatus(String id, String token, String userId) {
        return userRepository.checkUserLoginStatus(id, token, userId);
    }

    @Override
    public boolean updateUserProfile(String userId, String column, String value) {
        String changedColumn = changeUpperCamelCase(column);
        return userRepository.updateUserProfile(userId, changedColumn, value);
    }

    @Override
    public String changeUpperCamelCase(String changeVar) {
        final String regex = "(?=[A-Z][a-z])";
        final String subst = "_";

        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(changeVar);

        final String result = matcher.replaceAll(subst);

        return result.substring(1).toLowerCase();
    }

    @Override
    public Map getPersonalUserDetail(String login) {
        return userRepository.getPersonalUserDetail(login);
    }

    @Override
    public List<Map> getSearchResult(String user) {
        return userRepository.getSearchResult(user);
    }

    @Override
    public List<Map> getSearchTagResult(String tag) {
        return userRepository.getSearchTagResult(tag);
    }

}
