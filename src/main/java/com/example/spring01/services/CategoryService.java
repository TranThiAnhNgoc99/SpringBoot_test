package com.example.spring01.services;

import java.util.Optional;

import com.example.spring01.entity.Category;

public interface CategoryService {
	Iterable<Category> findAll();
	
	Optional<Category> findById(Long id);
	
	Category saveCategory(Category category);
	
	void deleteCategory(Long id);

}
