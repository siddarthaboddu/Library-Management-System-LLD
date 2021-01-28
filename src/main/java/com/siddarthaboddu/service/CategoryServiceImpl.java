package com.siddarthaboddu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.siddarthaboddu.entity.Category;
import com.siddarthaboddu.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Optional<List<Category>> getAllCategorys() {
		return Optional.of(categoryRepository.findAll());
	}

	@Override
	public Optional<Category> addCategory(Category category) {
		return Optional.ofNullable(categoryRepository.save(category));
	}

	@Override
	public Optional<Category> getCategoryById(Long categoryId) {
		return categoryRepository.findById(categoryId);
	}

}
