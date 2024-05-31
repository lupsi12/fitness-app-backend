package com.example.demo.services;

import com.example.demo.entities.user;
import com.example.demo.repos.UserRepository;
import com.example.demo.security.JwtUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserRepository userRepository;
	
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		List<user> user = userRepository.findByEmail(email);
		return JwtUserDetails.create(user.get(0));
	}
	
	public UserDetails loadUserById(Long id) {
		user user = userRepository.findById(id).get();
		return JwtUserDetails.create(user); 
	}

}
