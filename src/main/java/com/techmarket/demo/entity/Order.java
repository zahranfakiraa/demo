package com.techmarket.demo.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "created_date")
    private Date cratedDate;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "session_id")
    private String sessionId;

    // private List<OrderItem> orderItems;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public Order() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCratedDate() {
        return cratedDate;
    }

    public void setCratedDate(Date date) {
        this.cratedDate = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    // public List<OrderItem> getOrderItems() {
    // return orderItems;
    // }

    // public void setOrderItems(List<OrderItem> orderItems) {
    // this.orderItems = orderItems;
    // }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
