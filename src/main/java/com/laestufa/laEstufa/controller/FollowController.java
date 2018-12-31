package com.laestufa.laEstufa.controller;

import com.laestufa.laEstufa.model.FollowModel;
import com.laestufa.laEstufa.service.interfaces.FollowService;
import com.laestufa.laEstufa.service.interfaces.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class FollowController {

    @Autowired
    private FollowService followService;

    @Autowired
    private UserModelService userModelService;

    @RequestMapping(value = "/get/followers/{userId}", method = RequestMethod.GET)
    public List<Map> getUserFollowers(@PathVariable("userId") Integer userId) {
        return followService.getUserFollowers(userId);
    }

    @RequestMapping(value = "/get/following/{userId}", method = RequestMethod.GET)
    public List<Map> getUserFollowing(@PathVariable("userId") Integer userId) {
        return followService.getUserFollowing(userId);
    }

    @RequestMapping(value = "/get/follow", method = RequestMethod.POST)
    public boolean followUser(@RequestBody FollowModel newFollower,
                             @RequestHeader("Authorization") String token,
                             @RequestHeader("Session") String session,
                             @RequestHeader("UserId") String userId) {
        boolean res = false;
        if (userModelService.checkUserLoginStatus(session, token.substring(7), userId)) {
            try {
                res = followService.follow(newFollower);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    @RequestMapping(value = "/get/unfollow", method = RequestMethod.POST)
    public Boolean unfollowUser(@RequestBody FollowModel unfollow,
                                @RequestHeader("Authorization") String token,
                                @RequestHeader("Session") String session,
                                @RequestHeader("UserId") String userId) {
        boolean res = false;
        if (userModelService.checkUserLoginStatus(session, token.substring(7), userId)) {
            try {
                res = followService.unfollow(unfollow);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
