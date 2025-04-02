package com.example.webchat.services;


import com.example.webchat.DTOs.UserDTO;
import com.example.webchat.entities.User;
import com.example.webchat.repositories.UserRepository;
import com.mongodb.DuplicateKeyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public User saveUser(UserDTO userDTO){
        try {
            User newUser = new User();
            newUser.setUsername(userDTO.getUsername());
            newUser.setEmail(userDTO.getEmail());
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = bCryptPasswordEncoder.encode(userDTO.getPassword());
            newUser.setPassword(hashedPassword);

            return userRepository.save(newUser);
        }
        catch (DuplicateKeyException e){
            throw new RuntimeException("duplicate key present");
        }
    }



}
