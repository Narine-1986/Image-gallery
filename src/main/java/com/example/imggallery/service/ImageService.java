package com.example.imggallery.service;

import com.example.imggallery.model.Image;
import com.example.imggallery.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;
    private final MainService mainService;


    public void add(Image image, MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File upImage = new File(mainService.getUploadDir(), name);
        file.transferTo(upImage);
        image.setPicUrl(name);
        imageRepository.save(image);
    }

    public List<Image> allImgList() {
        return imageRepository.findAll();
    }

    public List<Image> imageList(int id){
        return imageRepository.findAllByCategoryId(id);
    }

    public void deleteImage(int id){
        imageRepository.deleteById(id);
    }

    public Image getImage(int id) {
        return imageRepository.getOne(id);
    }


  }


