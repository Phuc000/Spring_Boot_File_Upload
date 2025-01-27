package com.test1.test1.service;

import com.test1.test1.model.UserPhoto;
import com.test1.test1.repository.UserPhotoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPhotoService {

    private final UserPhotoRepository userPhotoRepository;

    public UserPhotoService(UserPhotoRepository userPhotoRepository) {
        this.userPhotoRepository = userPhotoRepository;
    }

    public void addUserPhoto(Integer userId, Integer photoId) {
        userPhotoRepository.save(new UserPhoto(userId, photoId));
    }
}