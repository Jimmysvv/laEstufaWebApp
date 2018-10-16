package com.laestufa.laEstufa.service;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

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
}
