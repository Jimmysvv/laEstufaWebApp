package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserPostsModel;
import com.laestufa.laEstufa.repository.UserPostsModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/get")
public class UserPostsController {

    @Autowired
    private UserPostsModelRepository userPostsModelRepository;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<UserPostsModel> getAllUserPosts() {
        List<UserPostsModel> allPostsList = userPostsModelRepository.findAll();
        return allPostsList;
    }
}
