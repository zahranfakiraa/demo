package com.techmarket.demo.dto.checkout;

public class CheckOutItemDto {
    private String productName;
    private int quantity;
    private double price;
    private long product_id;
    private int userId;

    public CheckOutItemDto() {
    }

    public CheckOutItemDto(String productName, int quantity, double price, long product_id, int userId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.product_id = product_id;
        this.userId = userId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(long product_id) {
        this.product_id = product_id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}
