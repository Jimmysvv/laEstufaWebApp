package com.laestufa.laEstufa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomePageController {

    @Autowired
    UserPostsController userPostsController;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcomePage (Model model) {
        model.addAttribute("title", "Home Page");
        //model.addAttribute("userPosts", userPostsController.getAllUserPosts());
        return "HomePage";
    }
}
