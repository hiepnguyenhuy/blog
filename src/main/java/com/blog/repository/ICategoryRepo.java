package com.blog.repository;

import com.blog.model.Blog;
import com.blog.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ICategoryRepo extends PagingAndSortingRepository<Category, Integer> {
}
