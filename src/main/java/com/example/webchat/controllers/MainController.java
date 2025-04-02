package com.example.webchat.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String getHomePage(Authentication authentication){
        return "Hello "+authentication.getName()+" !";
    }

}
