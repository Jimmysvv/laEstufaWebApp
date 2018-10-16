package com.laestufa.laEstufa.service;

import com.laestufa.laEstufa.model.UserPostsModel;
import com.laestufa.laEstufa.repository.UserPostsModelRepository;
import com.laestufa.laEstufa.service.interfaces.UserPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserPostsServiceImpl implements UserPostsService {

    @Autowired
    private UserPostsModelRepository userPostsModelRepository;

    private final String FILE_PATH = "C:\\Projekty\\";

    @Override
    public List<UserPostsModel> findAll() {
        return userPostsModelRepository.findAll();
    }

    @Override
    public List<Map> findAllUserPosts(String login) {
        return userPostsModelRepository.findAllUserPosts(login);
    }

    @Override
    public void save(UserPostsModel request) {
        userPostsModelRepository.save(request);
    }

    @Override
    public List<Map> findAllPosts() {
        return userPostsModelRepository.findAllPosts();
    }

    @Override
    public String getNewFileName(String fileName) {
        int lastIndexOf = fileName.lastIndexOf(".");
        if (lastIndexOf == -1) {
            throw new RuntimeException("File format error");
        }
        String newName = String.valueOf(TimeUnit.MILLISECONDS.toNanos(System.currentTimeMillis()));
        String ext = fileName.substring(lastIndexOf);
        return newName + ext;
    }

    @Override
    public boolean uploadFile(MultipartFile file, String fullName) {
        try {
            File upload = new File(FILE_PATH + fullName);
            upload.createNewFile();
            FileOutputStream fout = new FileOutputStream(upload);
            fout.write(file.getBytes());
            fout.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
