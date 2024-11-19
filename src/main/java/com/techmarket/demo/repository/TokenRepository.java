package com.techmarket.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.techmarket.demo.entity.AuthenticationToken;
import com.techmarket.demo.entity.User;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer>{
    AuthenticationToken findTokenByUser(User user);

    AuthenticationToken findTokenByToken(String token);
}
