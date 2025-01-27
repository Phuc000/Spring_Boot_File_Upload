package com.test1.test1.repository;

import com.test1.test1.model.UserPhoto;
import org.springframework.data.repository.CrudRepository;

public interface UserPhotoRepository extends CrudRepository<UserPhoto, Integer> {
}