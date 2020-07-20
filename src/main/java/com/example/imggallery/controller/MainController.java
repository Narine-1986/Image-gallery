package com.example.imggallery.controller;

import com.example.imggallery.model.Image;
import com.example.imggallery.service.CategoryService;
import com.example.imggallery.service.ImageService;
import com.example.imggallery.service.MainService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class MainController {

    private final CategoryService categoryService;
    private final ImageService imageService;
    public final MainService mainService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("allCategories", categoryService.allCategories());
        return "index1";
    }


    @GetMapping("/admin")
    public String admin(Model model){
        model.addAttribute("allCategories", categoryService.allCategories());
        model.addAttribute("allImages", imageService.allImgList());
        return "admin";
    }

    @GetMapping("/images")
    public String imagesByCat(@RequestParam("id") int id, Model model){
        List<Image> imgByCateg =  imageService.imageList(id);
        model.addAttribute("imgByCateg", imgByCateg);
        return "images";
    }

    @GetMapping(
            value = "/image",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    public @ResponseBody
    byte[] getImageWithMediaType(@RequestParam("name") String imageName) throws IOException {

        InputStream in = new FileInputStream(mainService.getUploadDir() + File.separator + imageName);
        return IOUtils.toByteArray(in);
    }
    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("allCategories", categoryService.allCategories());
        return "about";
    }

    @GetMapping("/blog")
    public String blog(Model model){
        model.addAttribute("allCategories", categoryService.allCategories());
        return "blog";
    }




}