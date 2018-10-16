package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.service.interfaces.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Controller
public class UserProfilePageController {

    @Autowired
    private UserModelService userModelService;

    @RequestMapping(value = "/loginStatus", method = RequestMethod.GET)
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    public Map getUserProfilePage (@PathVariable(value = "login", required = true) String login) {
//        a.stream().filter(distinctByKey(e -> e.getName())).forEach(e -> b.add(e.getName()));
        return userModelService.findUserByLogin(login);
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
