package com.example.imggallery.controller;

import com.example.imggallery.model.Category;
import com.example.imggallery.service.CategoryService;
import com.example.imggallery.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class CategoryContoller {

    private final CategoryService categoryService;
    public final MainService mainService;

    @PostMapping("/saveCategory")
    public String addCategory(@ModelAttribute Category category,
                              @RequestParam("image") MultipartFile file) throws IOException {
        categoryService.add(category, file);
        return "redirect:/admin";
    }

    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("id") int id) {
        categoryService.deleteCategory(id);
        return "redirect:/admin";
    }

    @GetMapping("/updateCategory")
    public String updateCategory(@RequestParam("id") int id, Model model) {
        model.addAttribute("catById", categoryService.getCategory(id));
        model.addAttribute("imgDirect",mainService.getUploadDir());
        return "updateCategory";
    }
}
