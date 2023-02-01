package com.blog.service;

import com.blog.model.Blog;
import com.blog.repository.IBlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogService {


    @Autowired
    IBlogRepo iBlogRepo;

    public List<Blog> getAll() {
        return (List<Blog>) iBlogRepo.findAll();
    }

    public Blog findById(int id) {
        return iBlogRepo.findById(id).get();
    }

    public void delete(int id) {
        iBlogRepo.deleteById(id);
    }


    public void save(Blog blog) {
        iBlogRepo.save(blog);
    }
}
