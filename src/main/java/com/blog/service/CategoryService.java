package com.blog.service;

import com.blog.model.Category;
import com.blog.repository.ICategoryRepo;
import jdk.internal.dynalink.linker.LinkerServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CategoryService {
    @Autowired
    ICategoryRepo iCategoryRepo;
    public Category findById (int id) {
      return   iCategoryRepo.findById(id).get();

    }

    public List<Category> getAll() {
      return (List<Category>) iCategoryRepo.findAll();
    }
}
