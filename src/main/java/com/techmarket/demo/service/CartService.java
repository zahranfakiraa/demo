package com.techmarket.demo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techmarket.demo.dto.cart.AddToCartDto;
import com.techmarket.demo.dto.cart.CartDto;
import com.techmarket.demo.dto.cart.CartItemDto;
import com.techmarket.demo.entity.Cart;
import com.techmarket.demo.entity.Product;
import com.techmarket.demo.entity.User;
import com.techmarket.demo.exception.CartItemNotExistException;
import com.techmarket.demo.repository.CartRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public CartService() {
    }

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(AddToCartDto addToCartDto, Product product, User user) {
        Cart cart = new Cart(product, user, addToCartDto.getQuantity());
        cartRepository.save(cart);
    }

    public CartDto listCartItems(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreateDateDesc(user);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart : cartList) {
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto : cartItems) {
            totalCost += (cartItemDto.getProduct().getPrice() * cartItemDto.getQuantity());
        }
        return new CartDto(cartItems, totalCost);
    }

    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }

    public void updateCartItem(AddToCartDto cartDto, User user, Product product) {
        @SuppressWarnings("deprecation")
        Cart cart = cartRepository.getOne(cartDto.getId());
        cart.setQuantity(cartDto.getQuantity());
        cart.setCreateDate(new Date());
        cartRepository.save(cart);
    }

    public void deleteCartItem(int id, int userId) throws CartItemNotExistException {
        if (!cartRepository.existsById(id))
            throw new CartItemNotExistException("Cart id is invalid : " + id);
        cartRepository.deleteById(id);

    }

    public void deleteCartItems(int userId) {
        cartRepository.deleteAll();
    }

    public void deleteUserCartItems(User user) {
        cartRepository.deleteByUser(user);
    }

}
