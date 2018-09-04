package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class RegistrationPageController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(method = RequestMethod.GET)
    public String getRegistrationPage(Model model) {
        return "RegistrationPage";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public UserModel addNewUser(@Valid @RequestBody UserModel user) {
        return userRepository.save(user);
    }

}
