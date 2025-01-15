package com.test1.test1.service;

import com.test1.test1.model.Photo;
import com.test1.test1.repository.PhotoRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// @Component
@Service
public class PhotoService {

    private final PhotoRepository photoRepository;

    public PhotoService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public Iterable<Photo> getPhotos() {
        return photoRepository.findAll();
    }

    public Photo getPhoto(Integer id) {
        return photoRepository.findById(id).orElse(null);
    }

    public void remove(Integer id) {
        photoRepository.deleteById(id);
    }

    public int getPhotoSize() {
        return (int) photoRepository.count();
    }

    public Photo put(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        photo.setFileName(filename);
        photo.setData(data);
        photo.setContentType(contentType);
        photoRepository.save(photo);
        return photo;
    }
}
