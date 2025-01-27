package com.test1.test1.web;

import com.test1.test1.PhotoNotFoundException;
import com.test1.test1.model.Photo;
import com.test1.test1.model.User;
import com.test1.test1.service.PhotoService;
import com.test1.test1.service.UserPhotoService;
import com.test1.test1.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

@RestController
public class PhotoController {

    private final PhotoService photoService;

    private final UserService userService;

    private final UserPhotoService userPhotoService;

    public PhotoController(PhotoService photoService, UserService userService, UserPhotoService userPhotoService) {
        this.photoService = photoService;
        this.userService = userService;
        this.userPhotoService = userPhotoService;
    }

//    List<Photo> photos = List.of(new Photo("photo1", "1"), new Photo("photo2", "2"));

    @GetMapping("/photo")
    public Iterable<Photo> getPhotos() {
        return photoService.getPhotos();
    }

    @GetMapping("/photo/{id}")
    public Photo getPhotoById(@PathVariable Integer id) throws PhotoNotFoundException {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
            throw new PhotoNotFoundException();
        }
        return photo;
    }

    @DeleteMapping("/photo/{id}")
    public void deletePhotoById(@PathVariable Integer id) throws PhotoNotFoundException {
        Photo photo = photoService.getPhoto(id);
        if (photo == null) {
            throw new PhotoNotFoundException();
        }
        photoService.remove(id);
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

    @PostMapping("/real_photo_with_user")
    public Photo addRealPhoto(@RequestPart("data") MultipartFile file, HttpServletRequest request) throws IOException {
        Claims claims = (Claims) request.getAttribute("claims");
        String username = claims.getSubject();
        User user = userService.getUserByUsername(username);
        Photo photo = photoService.put(file.getOriginalFilename(), file.getContentType(), file.getBytes());
        userPhotoService.addUserPhoto(user.getId(), photo.getId());
        return photo;
    }


}
