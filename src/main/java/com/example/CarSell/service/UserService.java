package com.example.CarSell.service;

import com.example.CarSell.domain.Role;
import com.example.CarSell.domain.User;
import com.example.CarSell.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean addUser(User user) {
        User userMatch = userRepository.findByUsername(user.getUsername());
        if(userMatch != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);
        return true;

    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }
}
