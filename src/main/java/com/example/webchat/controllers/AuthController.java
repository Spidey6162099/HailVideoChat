package com.example.webchat.controllers;

import com.example.webchat.DTOs.UserDTO;
import com.example.webchat.entities.User;
import com.example.webchat.services.TokenService;
import com.example.webchat.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    private static final Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private final TokenService tokenService;

    @Autowired
    private UserService userService;
    public AuthController(TokenService tokenService) {
        this.tokenService = tokenService;
    }


    @PostMapping("/v1/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO userDTO){
        try{
            User user=userService.saveUser(userDTO);
            user.setPassword("");
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        catch(Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error signing up");
        }
    }

    //used to recieve the jwt token when username and password available
    @PostMapping("/v1/login")
    public ResponseEntity<?> token(Authentication authentication) {
        try {
            System.out.println(authentication.getName());
            String token = tokenService.generateToken(authentication);
            return ResponseEntity.status(HttpStatus.OK).body(token);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("error logging in");
        }


    }

}
