package com.test1.test1.service;

import com.test1.test1.model.Photo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

// @Component
@Service
public class PhotoService {

    private Map<String, Photo> db = new HashMap<>() {{
        put("1", new Photo("photo1", "1"));
        put("2", new Photo("photo2", "2"));
    }};

    public Collection<Photo> getPhotos() {
        return db.values();
    }

    public Photo getPhoto(String id) {
        return db.get(id);
    }

    public Photo remove(String id) {
        return db.remove(id);
    }

    public int getPhotoSize() {
        return db.size();
    }

    public Photo put(String filename, String contentType, byte[] data) {
        Photo photo = new Photo();
        String uuid = UUID.randomUUID().toString();
        photo.setId(uuid);
        photo.setName(filename);
        photo.setData(data);
        photo.setContentType(contentType);
        db.put(uuid, photo);
        return photo;
    }
}
