package com.example.imggallery.repository;

import com.example.imggallery.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image,Integer> {


    List<Image>findAllByCategoryId(int id);

}
