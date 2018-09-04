package com.laestufa.laEstufa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomePageController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWelcomePage (Model model) {
        model.addAttribute("title", "Home Page");
        return "WelcomePage";
    }
}
