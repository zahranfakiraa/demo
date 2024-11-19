package com.techmarket.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmarket.demo.common.ApiResponse;
import com.techmarket.demo.dto.cart.AddToCartDto;
import com.techmarket.demo.dto.cart.CartDto;
import com.techmarket.demo.entity.Product;
import com.techmarket.demo.entity.User;
import com.techmarket.demo.exception.AuthenticationFailException;
import com.techmarket.demo.exception.CartItemNotExistException;
import com.techmarket.demo.exception.ProductNotExistException;
import com.techmarket.demo.service.AuthenticationService;
import com.techmarket.demo.service.CartService;
import com.techmarket.demo.service.ProductService;

@RestController
@RequestMapping("/cart")
public class CarController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
            @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        System.out.println("token user: " + token);
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(addToCartDto.getProductId());
        System.out.println("product to add" + product.getName());
        cartService.addToCart(addToCartDto, product, user);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);

    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token)
            throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        CartDto cartDto = cartService.listCartItems(user);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }

    @PutMapping("/update/{cartItemId}")
    public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
            @RequestParam("token") String token) throws AuthenticationFailException, ProductNotExistException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        Product product = productService.getProductById(cartDto.getProductId());
        cartService.updateCartItem(cartDto, user, product);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,
            @RequestParam("token") String token) throws AuthenticationFailException, CartItemNotExistException {
        authenticationService.authenticate(token);
        int userId = authenticationService.getUser(token).getId();
        cartService.deleteCartItem(itemID, userId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
    }
}
