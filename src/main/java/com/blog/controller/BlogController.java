package com.blog.controller;

import com.blog.model.Blog;
import com.blog.model.Category;
import com.blog.service.BlogService;
import com.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @ModelAttribute("categories")
    public List<Category> categories() {
        return categoryService.getAll();
    }

    @Autowired
    CategoryService categoryService;

    @GetMapping("/blog/{id}")
    public ModelAndView showBlog(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("showOneBlog");
        modelAndView.addObject("blog",blogService.findById(id));
        return modelAndView;
    }


    @GetMapping("/blogs")
    public ModelAndView show() {
        ModelAndView modelAndView = new ModelAndView("showBlog");
        modelAndView.addObject("blogs", blogService.getAll());
        return modelAndView;
    }

    @GetMapping("/save")
    public ModelAndView createBlog() {
        ModelAndView modelAndView = new ModelAndView("createBlog");
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView createBlog(@ModelAttribute Blog blog, @RequestParam MultipartFile fileImg) {
        String nameFile = fileImg.getOriginalFilename();
        try {
            FileCopyUtils.copy(fileImg.getBytes(), new File("D:\\Code gym\\Modul4\\buoi1\\blog\\src\\main\\webapp\\WEB-INF\\img\\" + nameFile));
        } catch (Exception e) {
            e.printStackTrace();
        }
        blog.setImg("/img/" + nameFile);
        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");

        return modelAndView;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        blogService.delete(id);
        return "redirect:/blogs";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("editBlog");
        modelAndView.addObject("blog", blogService.findById(id));
        modelAndView.addObject("categories", categoryService.getAll());
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView edit(@ModelAttribute Blog blog, @RequestParam MultipartFile fileImg) {
        if (!fileImg.isEmpty()) {
            try {
                FileCopyUtils.copy(fileImg.getBytes(),
                        new File("D:\\Code gym\\Modul4\\buoi1\\blog\\src\\main\\webapp\\WEB-INF" + blog.getImg()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        blogService.save(blog);
        ModelAndView modelAndView = new ModelAndView("redirect:/blogs");
        return modelAndView;
    }


}
