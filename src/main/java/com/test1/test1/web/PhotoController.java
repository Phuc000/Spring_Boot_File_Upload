package com.test1.test1.web;

import com.test1.test1.PhotoNotFoundException;
import com.test1.test1.model.Photo;
import com.test1.test1.service.PhotoService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
public class PhotoController {

    private final PhotoService photoService;

    public PhotoController(PhotoService photoService) {
        this.photoService = photoService;
    }

    List<Photo> photos = List.of(new Photo("photo1", "1"), new Photo("photo2", "2"));

    @GetMapping("/photo")
    public Collection<Photo> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhotoById(@PathVariable String id) throws PhotoNotFoundException {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
            throw new PhotoNotFoundException();
        }
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhotoById(@PathVariable String id) throws PhotoNotFoundException {
        Photo photo = photoService.remove(id);
        if (photo == null) {
            throw new PhotoNotFoundException();
        }
    }

//    @PostMapping("/photo")
//    public Photo addPhoto(@RequestBody @Valid Photo photo) {
//        photo.setId(String.valueOf(photoService.getPhotoSize() + 10));
//        photoService.put(photo.getId(), photo);
//        return photo;
//    }

    @PostMapping("/real_photo")
    public Photo addRealPhoto(@RequestPart("data") MultipartFile file) throws IOException {
        // generate a random uuid for the photo
        return photoService.put(file.getOriginalFilename(), file.getContentType(), file.getBytes());
    }


}
