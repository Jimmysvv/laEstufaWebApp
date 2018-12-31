package com.laestufa.laEstufa.service.interfaces;

import com.laestufa.laEstufa.model.UserPostsModel;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserPostsService {

    List<UserPostsModel> findAll();

    List<Map> findAllUserPosts(final String login);

    void save(UserPostsModel request);

    List<Map> findAllPosts();

    String getNewFileName(String fileName);

    boolean uploadFile(MultipartFile file, String fullName);

    List<Map> getCurrentUserPosts(String postId);

    List<Map> getAllFollowingPosts(String userId);
}
