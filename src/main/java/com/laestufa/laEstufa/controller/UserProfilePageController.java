package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@RestController
public class UserProfilePageController {

    @Autowired
    private UserModelService userModelService;

    @RequestMapping(value = "/loginStatus", method = RequestMethod.GET)
    public Map checkUserLoginStatus(@RequestHeader("Authorization") String token,
                                    @RequestHeader("Session") String session,
                                    @RequestHeader("UserId") String userId) {
        boolean res = false;
        Map<String, Boolean> map = new HashMap<>();
        try {
            res = userModelService.checkUserLoginStatus(session, token.substring(7), userId);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        map.put("status", res);
        return map;
    }

    @RequestMapping(value = "/get/logout", method = RequestMethod.GET)
    public Map doLogout(@RequestHeader("Authorization") String token,
                        @RequestHeader("Session") String session) {
        boolean res = false;
        Map<String, Boolean> map = new HashMap<>();
        try {
            res = userModelService.deleteUserSession(session, token);
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        map.put("status", res);
        return map;
    }

    @RequestMapping(value = "/give/user/{login}", method = RequestMethod.GET)
    public Map getUserByLogin(@PathVariable(value = "login", required = true) String login) {
//        a.stream().filter(distinctByKey(e -> e.getName())).forEach(e -> b.add(e.getName()));
        return userModelService.findUserByLogin(login);
    }

    @RequestMapping(value = "/get/user/{login}", method = RequestMethod.GET)
    public Map getPersonalUserDetail(@PathVariable(value = "login", required = true) String login,
                                  @RequestHeader("Authorization") String token,
                                  @RequestHeader("Session") String session,
                                  @RequestHeader("UserId") String userId) {
        if(userModelService.checkUserLoginStatus(session, token.substring(7), userId)) {
            return userModelService.getPersonalUserDetail(login);
        } else {
            return null;
        }
    }

    @RequestMapping(value = "/get/user/edit", method = RequestMethod.POST)
    public Map editUserProfile(@RequestBody UserModel newUserData,
                               @RequestHeader("Authorization") String token,
                               @RequestHeader("Session") String session,
                               @RequestHeader("UserId") String userId) {

        Boolean res = false;
        Map<String, String> map = new HashMap<>();
        if(userModelService.checkUserLoginStatus(session, token.substring(7), userId)) {
            try {
                ReflectionUtils.doWithFields(UserModel.class, field -> {
                    field.setAccessible(true);
                    if (field.get(newUserData) != null) {
                        userModelService.updateUserProfile(userId, field.getName(), field.get(newUserData).toString());
                        map.put(userModelService.changeUpperCamelCase(field.getName()), field.get(newUserData).toString());
                    }
                });
                res = true;
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        map.put("status", res.toString());
        return map;
    }

    @RequestMapping(value = "/get/searching/{type}", method = RequestMethod.GET)
    public List<Map> getSearchResult(@PathVariable(value = "type", required = true) String type,
                                     @RequestHeader("value") String value) {
        List<Map> resultList = new ArrayList<>();
        try {
            switch (type) {
                case "people" :
                    resultList = userModelService.getSearchResult(value);
                    break;
                case "tag" :
                    resultList = userModelService.getSearchTagResult(value);
                    break;
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
