package com.techmarket.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.demo.entity.Cart;
import com.techmarket.demo.entity.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    

    List<Cart> deleteByUser(User user);

    List<Cart> findAllByUserOrderByCreateDateDesc(User user);
}
