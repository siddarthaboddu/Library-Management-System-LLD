package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import com.siddarthaboddu.entity.Category;

public interface CategoryService {

	Optional<List<Category>> getAllCategorys();

	Optional<Category> addCategory(Category category);

	Optional<Category> getCategoryById(Long categoryId);

}
