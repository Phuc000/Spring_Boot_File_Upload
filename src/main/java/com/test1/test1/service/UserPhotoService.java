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
        UserPhoto userPhoto = new UserPhoto(userId, photoId);
        // log the userPhoto to the console
        System.out.println(userPhoto.show());
        // add the userPhoto to the database
        userPhotoRepository.save(userPhoto);
    }
}