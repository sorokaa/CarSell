package com.example.servingWebContent;

import com.example.domain.User;

import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/")
    public String greetingX() {
        return "greeting";
    }

    @GetMapping("/greeting")
    public String greeting() {
        return "greeting";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/readMore")
    public String readMore() {
        return "readMore";
    }

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name
            , @RequestParam String email, @RequestParam String password) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request

        User n = new User();

        n.setName(name);
        n.setEmail(email);
        n.setPassword(password);

        repository.save(n);
        return "Saved";
    }

    @GetMapping
    public String getAllUsers(Map<String, Object> model) {
        Iterable<User> users = repository.findAll();
        model.put("users", users);
        return "/greeting";
    }

}
