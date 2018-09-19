package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Controller
@RequestMapping(value = "/get")
public class UserProfilePageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/user/{login}", method = RequestMethod.GET)
    @ResponseBody
    public UserModel getUserProfilePage (@PathVariable(value = "login", required = true) String login) {

        UserModel userModel = userRepository.findUserByLogin(login);
//        a.stream().filter(distinctByKey(e -> e.getName())).forEach(e -> b.add(e.getName()));

        return userModel;
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
