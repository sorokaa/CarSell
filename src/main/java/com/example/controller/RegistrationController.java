package com.example.controller;

import com.example.domain.Role;
import com.example.domain.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {

        User usrFromOb = userRepo.findByEmail(user.getEmail());

        if(usrFromOb != null) {
            model.put("message", "User exist");
            return "registration";
        }

        user.setRole(Collections.singleton(Role.USER));
        userRepo.save(user);
        return "redirect:/login";
    }

}
