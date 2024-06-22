package com.techmarket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.demo.entity.OrderItem;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

}
