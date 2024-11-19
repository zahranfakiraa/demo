package com.techmarket.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.demo.dto.product.ProductDto;
import com.techmarket.demo.entity.Category;
import com.techmarket.demo.entity.Product;
import com.techmarket.demo.exception.ProductNotExistException;
import com.techmarket.demo.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<ProductDto> listProduct(){
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>(); 

        for(Product product : products){
            ProductDto productDto = getDtoFromProduct(product);
            productDtos.add(productDto);
        }
        return productDtos;
    }

    public static ProductDto getDtoFromProduct(Product product){
        ProductDto productDto = new ProductDto(product);
        return productDto;
    }

    public static Product getProductFromDto(ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto, category);
        return product;
    }

    public void addProduct(ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    public void updateProduct(Integer productId, ProductDto productDto, Category category){
        Product product = getProductFromDto(productDto, category);
        product.setId(productId);
        productRepository.save(product);
    }

    public Product getProductById(Integer productId) throws ProductNotExistException{
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(!optionalProduct.isPresent())
            throw new ProductNotExistException("Product id is not valid " + productId);
            return optionalProduct.get();
    }
}
