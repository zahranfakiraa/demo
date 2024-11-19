package com.techmarket.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.demo.entity.OrderItem;
import com.techmarket.demo.repository.OrderItemRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemsRepository;

    public void addOrderedProducts(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }

}
