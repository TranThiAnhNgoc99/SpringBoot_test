package com.example.spring01.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.spring01.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
