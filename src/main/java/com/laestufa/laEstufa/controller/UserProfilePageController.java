package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

@Controller
public class UserProfilePageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getUserProfilePage (Model model) {

//        userRepository.findAll().forEach((UserModel e) -> model.addAttribute("Users", e));

        List<UserModel> a = (List<UserModel>) userRepository.findAll();
        List<String> b = new ArrayList<>();
        a.stream().filter(distinctByKey(e -> e.getName())).forEach(e -> b.add(e.getName()));

        model.addAttribute("Users", userRepository.findAll());
        model.addAttribute("names", b);

        return "UserProfilePage";
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object,Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }
}
