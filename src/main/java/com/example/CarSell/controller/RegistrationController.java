package com.example.CarSell.controller;

import com.example.CarSell.domain.Role;
import com.example.CarSell.domain.User;
import com.example.CarSell.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          Map<String, Object> model) {

        if ((user.getPassword() == null) || (user.getPassword().length() <= 4)) {
            model.put("errors", "Password error. May be longer than 4 characters");
            return "registration";
        }

        if (!userService.addUser(user)) {
            model.put("errors", "User exist!");
            return "registration";
        }

        return "redirect:/login";
    }
}