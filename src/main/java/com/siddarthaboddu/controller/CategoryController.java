package com.siddarthaboddu.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siddarthaboddu.entity.Category;
import com.siddarthaboddu.service.CategoryService;

@RestController
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ResponseEntity<List<Category>> getAllCategorys(){
		Optional<List<Category>> categoryList = categoryService.getAllCategorys();
		
		if(categoryList.isEmpty() || categoryList.get().isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<>(categoryList.get(), HttpStatus.OK);
	}
	
	@PostMapping("/categories")
	public ResponseEntity<Category> addCategory(@RequestBody Category Category){
		Optional<Category> categoryResponseOptional = categoryService.addCategory(Category);
		
		if(categoryResponseOptional.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(categoryResponseOptional.get(), HttpStatus.CREATED);
	}
}
