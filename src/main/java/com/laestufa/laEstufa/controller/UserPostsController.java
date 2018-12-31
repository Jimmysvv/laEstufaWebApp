package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.UserPostsModel;
import com.laestufa.laEstufa.service.interfaces.FollowService;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import com.laestufa.laEstufa.service.interfaces.UserPostsService;
import com.sun.istack.internal.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.http.HTTPException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserPostsController {


    @Autowired
    private UserPostsService userPostsService;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private FollowService followService;

    private final Logger log = Logger.getLogger(getClass());

    @RequestMapping(value = "/give/all", method = RequestMethod.GET)
    public List<Map> findAllPosts() {
        return userPostsService.findAllPosts();
    }

    @RequestMapping(value = "/give/all/{login}", method = RequestMethod.GET)
    public List<Map> findAllUserPosts(@PathVariable("login") String login) {
        return userPostsService.findAllUserPosts(login);
    }

    @RequestMapping(value = "/give/current/{postId}", method = RequestMethod.GET)
    public List<Map> getCurrentUserPosts(@PathVariable("postId") String postId) {
        return userPostsService.getCurrentUserPosts(postId);
    }

    @RequestMapping(value = "/get/followingPosts/{userId}", method = RequestMethod.GET)
    public List<Map> getAllFollowingPosts(@PathVariable("userId") String userId) {
        return userPostsService.getAllFollowingPosts(userId);
    }

    @RequestMapping(value = "/get/post/create", method = RequestMethod.POST)
    public Map createNewPost(@RequestBody UserPostsModel request,
                                    @RequestHeader("Authorization") String token,
                                    @RequestHeader("Session") String session,
                                    @RequestHeader("UserId") String userId) {

        if (userModelService.checkUserLoginStatus(session, token.substring(7), userId)) {
            try {
                Map<String, String> res = new HashMap<>();
                userPostsService.save(request);
                res.put("message", HttpStatus.OK.getReasonPhrase());
                res.put("success", String.valueOf(true));
                return res;
            } catch (HTTPException e) {
                log.warning("Recived exception: ", e);
            }
        }

        return null;
    }

    @RequestMapping(value = "/get/post/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map upload(@RequestBody MultipartFile file,
                      @RequestHeader("Authorization") String token,
                      @RequestHeader("Session") String session,
                      @RequestHeader("UserId") String userId) {

        if (userModelService.checkUserLoginStatus(session, token.substring(7), userId)) {
            String fileName = file.getOriginalFilename();
            String fullName = userPostsService.getNewFileName(fileName);
            Map<String, String> res = new HashMap<>();
            if (userPostsService.uploadFile(file, fullName)) {
                res.put("message", fullName);
                res.put("success", String.valueOf(true));
                return res;
            }
        }
        return null;
    }
}
