package com.example.demo.controllers;

import com.example.demo.entities.admin;
import com.example.demo.entities.user;
import com.example.demo.exceptions.UserNotFoundException;
import com.example.demo.repos.UserRepository;
import com.example.demo.responses.userResponses;
import com.example.demo.services.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class userController {
    @Autowired
    private userService userService;
    private UserRepository userRepository;

    public userController(userService userService) {
        this.userService = userService;
    }
    @GetMapping
    public List<userResponses> getAllUsers(@RequestParam Optional<String> tur,@RequestParam Optional<String> email){
        return userService.getAllUsers(tur,email);
    }

    /*
    @GetMapping("/{tur}")
    public List<user> getAllAdminUsers(@PathVariable("tur") String tur){
        return userService.getAllAdminUsers(tur);
    }*/

    @PostMapping
    public user createUser(@RequestBody user newUser){
        return userService.saveOneUser(newUser);
    }


    @GetMapping("/{userId}")
    public List<user> getOneUser(@PathVariable Long userId){
        user user = userService.getOneUser(userId);
        List<user> users =new ArrayList<>();
        users.add(user);
        return users;
    }



    @PutMapping("/{userId}")
    public user updateOneUser(@PathVariable Long userId, @RequestBody user newUser){
        return userService.updateOneUser(userId,newUser);
    }
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        userService.deleteOneUser(userId);
    }



}

