package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserPostsModel;
import com.laestufa.laEstufa.repository.UserPostsModelRepository;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.http.HTTPException;
import java.util.List;

@Controller
@RequestMapping(value = "/get")
public class UserPostsController {

    @Autowired
    private UserPostsModelRepository userPostsModelRepository;

    protected final Logger log = Logger.getLogger(getClass());

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @ResponseBody
    public List<UserPostsModel> getAllUserPosts() {
        List<UserPostsModel> allPostsList = userPostsModelRepository.findAll();
        return allPostsList;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public HttpStatus createNewPost(@RequestBody UserPostsModel request) {
        try {
            userPostsModelRepository.save(request);
            return HttpStatus.OK;
        } catch (HTTPException e) {
            log.warning("Recived exception: ", e);
            return HttpStatus.valueOf(e.getStatusCode());
        }
    }
}
