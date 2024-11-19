package com.techmarket.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.demo.entity.Order;
import com.techmarket.demo.entity.User;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserOrderByCratedDateDesc(User user);
}
