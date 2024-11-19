package com.techmarket.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techmarket.demo.common.ApiResponse;
import com.techmarket.demo.entity.Category;
import com.techmarket.demo.service.CategoryService;
import com.techmarket.demo.utils.Helper;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("/")
    public ResponseEntity<List<Category>> getCategoryList(){
        List<Category> body = categoryService.listCategories();
        return new ResponseEntity<List<Category>>(body, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createCategory(@Validated @RequestBody Category category){
        if(Helper.notNull(categoryService.readCategory(category.getCategoryName()))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category already exist"), HttpStatus.CONFLICT);
        }
        categoryService.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Created the category"), HttpStatus.CREATED);
    }

    @PostMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") Integer categoryId, @Validated @RequestBody Category category){
        if(Helper.notNull(categoryService.readCategory(categoryId))){
            return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Updated the category"), HttpStatus.OK);
        }
        return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.NOT_FOUND);
    }
}
