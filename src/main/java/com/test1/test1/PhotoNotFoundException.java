package com.test1.test1;

public class PhotoNotFoundException extends Throwable {
    // log error msg on the console
    public PhotoNotFoundException() {
        System.err.println("Photo not found");
    }
}
