package com.techmarket.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techmarket.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    
    List<User> findAll(); 
    
    User findByEmail(String email);

    User findUserByEmail(String email);
}
