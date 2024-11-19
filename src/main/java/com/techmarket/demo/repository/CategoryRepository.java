package com.techmarket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.demo.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Category findByCategoryName(String categoryName);
}
