package com.laestufa.laEstufa.service;

import com.laestufa.laEstufa.model.FollowModel;
import com.laestufa.laEstufa.repository.FollowRepository;
import com.laestufa.laEstufa.service.interfaces.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Override
    public List<Map> getUserFollowers(Integer userId) {
        return followRepository.getUserFollowers(userId);
    }

    @Override
    public List<Map> getUserFollowing(Integer userId) {
        return followRepository.getUserFollowing(userId);
    }

    @Override
    public boolean follow(FollowModel newFollower) {
        return followRepository.follow(newFollower.getFollower(), newFollower.getFollowing());
    }

    @Override
    public boolean unfollow(FollowModel unfollow) {
        return followRepository.unfollow(unfollow.getFollower(), unfollow.getFollowing());
    }

    @Override
    public List<Map> getUserFollowCounter(Integer userId) {
        return followRepository.getUserFollowCounter(userId);
    }
}
