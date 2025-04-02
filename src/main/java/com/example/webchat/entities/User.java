package com.example.webchat.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @Indexed(unique = true)
    private String username;

    private String password;

    private String email;

    private List<String> friends=new ArrayList<>();

    //eg-> ROLE_USER,ROLE_ADMIN etc..
    private List<String> roles=new ArrayList<>();

    public List<String> getRoles() {
        return roles;
    }
    public String getPassword(){
        return password;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
