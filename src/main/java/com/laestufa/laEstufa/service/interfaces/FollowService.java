package com.laestufa.laEstufa.service.interfaces;

import com.laestufa.laEstufa.model.FollowModel;

import java.util.List;
import java.util.Map;

public interface FollowService {

    List<Map> getUserFollowers(final Integer userId);

    List<Map> getUserFollowing(final Integer userId);

    boolean follow(final FollowModel newFollower);

    boolean unfollow(final FollowModel unfollow);

    List<Map> getUserFollowCounter(final Integer userId);
}
