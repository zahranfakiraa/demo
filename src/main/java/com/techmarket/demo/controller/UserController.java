package com.techmarket.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techmarket.demo.dto.ResponseDto;
import com.techmarket.demo.dto.user.SignInDto;
import com.techmarket.demo.dto.user.SignInResponseDto;
import com.techmarket.demo.dto.user.SignUpDto;
import com.techmarket.demo.entity.User;
import com.techmarket.demo.exception.AuthenticationFailException;
import com.techmarket.demo.exception.CustomeException;
import com.techmarket.demo.repository.UserRepository;
import com.techmarket.demo.service.AuthenticationService;
import com.techmarket.demo.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    UserService userService;

    @GetMapping("/all")
    public User findUser(@RequestParam("token") String token) throws AuthenticationFailException {
        authenticationService.authenticate(token);
        User user = authenticationService.getUser(token);
        user.getEmail();
        return userRepository.findUserByEmail(user.getEmail());
    }

    @PostMapping("/signup")
    public ResponseDto signUp(@RequestBody SignUpDto signUpDto) throws CustomeException {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInResponseDto signIn(@RequestBody SignInDto signInDto) throws CustomeException {
        return userService.signIn(signInDto);
    }

}
