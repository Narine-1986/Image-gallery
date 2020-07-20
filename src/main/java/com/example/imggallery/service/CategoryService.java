package com.example.imggallery.service;

import com.example.imggallery.model.Category;
import com.example.imggallery.model.Image;
import com.example.imggallery.repository.CategoryRepository;
import com.example.imggallery.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ImageRepository imageRepository;
    private final MainService mainService;


    public void add(Category category, MultipartFile file) throws IOException {
        String name = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File image = new File(mainService.getUploadDir(), name);
        file.transferTo(image);
        category.setPicUrl(name);
        categoryRepository.save(category);
    }

    public List<Category> allCategories() {
        return categoryRepository.findAll();
    }

    public void deleteCategory(int id){
        List<Image> images = imageRepository.findAllByCategoryId(id);
        imageRepository.deleteAll(images);
        categoryRepository.deleteById(id);
    }

    public Category getCategory(int id) {
        return categoryRepository.getOne(id);
    }



}