package com.example.imggallery.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MainService {

    @Value("C:\\JavaEE\\Image-gallery\\upload")
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }
}
