package com.example.webchat.services;


import com.example.webchat.entities.User;
import com.example.webchat.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private  UserRepository userRepository;

    public User findUser(String username){

            Optional<User> optionalUser=userRepository.findUserByUsername(username);

            //if not found throw exceptiom
            return optionalUser.orElseThrow(()-> new UsernameNotFoundException("user not found"));
    }



}
