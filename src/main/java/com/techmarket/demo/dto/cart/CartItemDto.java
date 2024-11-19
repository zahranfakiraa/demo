package com.techmarket.demo.dto.cart;

import com.techmarket.demo.entity.Cart;
import com.techmarket.demo.entity.Product;

public class CartItemDto {
    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItemDto(Cart cart) {
    }

    @Override
    public String toString() {
        return "CartItemDto [id=" + id + ", quantity=" + quantity + ", product=" + product + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
