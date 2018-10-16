package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserModel;
import com.laestufa.laEstufa.repository.UserRepository;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/register")
public class RegistrationPageController {

    @Autowired
    private UserModelService userModelService;

    @RequestMapping(value = "/new/user", method = RequestMethod.POST)
    @ResponseBody
    public String addNewUser(@RequestBody UserModel user,
                             @RequestHeader("Register") boolean header) {

        if(!header || !checkIfUserExists(user)) {
            throw new RuntimeException("Error on registration");
        }

        return userModelService.createNewUser(user) ?
                HttpStatus.CREATED.toString() : HttpStatus.INTERNAL_SERVER_ERROR.toString();
    }

    private boolean checkIfUserExists(UserModel user) {
        return userModelService.checkForExistingUser(user);
    }
}
