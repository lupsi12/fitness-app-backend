package com.example.demo.services;

import com.example.demo.entities.user;
import com.example.demo.repos.UserRepository;
import com.example.demo.responses.userResponses;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class userService {
    private PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    public userService(UserRepository userRepository,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<userResponses> getAllUsers(Optional<String> tur,Optional<String> email) {
        List<user> list = null;
        if(tur.isPresent() && email.isPresent()) {
            list =userRepository.findByTurAndEmail(tur.get(),email.get());
        }
        else if (tur.isPresent()) {
            list = userRepository.findByTur(tur.get());

        }
        else if (email.isPresent()) {
           list = userRepository.findByEmail(email.get());
        } else list = userRepository.findAll();
        return list.stream().map(p -> new userResponses(p)).collect(Collectors.toList());
    }

    public user saveOneUser(user newUser) {
            return userRepository.save(newUser);
    }

    public void deleteOneUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public user updateOneUser(Long userId, user newUser) {
        Optional<user> user = userRepository.findById(userId);
        if(user.isPresent()){
            user foundUser = user.get();
            foundUser.setName(newUser.getName());
            foundUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
            foundUser.setSurname(newUser.getSurname());
            foundUser.setDateofbirth(newUser.getDateofbirth());
            foundUser.setDateofregistration(newUser.getDateofregistration());
            foundUser.setGender(newUser.getGender());
            foundUser.setEmail(newUser.getEmail());
            foundUser.setTelephonenumber(newUser.getTelephonenumber());
            foundUser.setTur(newUser.getTur());
            userRepository.save(foundUser);
            return foundUser;
        }else
            return null;
    }

    public List<user> getOneUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public List<user> getAllAdminUsers(String tur) {
        return userRepository.findByTur(tur);
    }


    public user getOneUser(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }
}
