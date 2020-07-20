package com.example.imggallery.controller;

import com.example.imggallery.model.Image;
import com.example.imggallery.service.CategoryService;
import com.example.imggallery.service.ImageService;
import com.example.imggallery.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@Controller

public class ImageController {

    private final ImageService imageService;
    private final CategoryService categoryService;
    public final MainService mainService;

    @PostMapping("/saveImage")
    public String addImage(@ModelAttribute Image image,
                              @RequestParam("image") MultipartFile file) throws IOException {
        imageService.add(image, file);
        return "redirect:/admin";
    }

    @GetMapping("/deleteImage")
    public String deleteImage(@RequestParam("id") int id){
        imageService.deleteImage(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateImage")
    public String updateImage(@RequestParam("id") int id, Model model){
        model.addAttribute("imageById", imageService.getImage(id));
        model.addAttribute("imgDirect", mainService.getUploadDir());
        model.addAttribute("allCategories", categoryService.allCategories());
        return "updateImage";
    }


}
