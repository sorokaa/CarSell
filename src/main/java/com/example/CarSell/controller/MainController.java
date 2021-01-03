package com.example.CarSell.controller;

import com.example.CarSell.domain.Post;
import com.example.CarSell.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    private Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/")
    public String mainX() {
        return "main";
    }

    @GetMapping("/help")
    public String help() {
        return "help";
    }

    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    @GetMapping("/posting-page")
    public String carsPage(Map<String, Object> model) {
        Iterable<Post> all = postRepository.findAll();
        model.put("cars", all);
        return "posting-page";
    }

    @PostMapping("/add")
    public String add(@RequestParam String link, @RequestParam String mainInfo, @RequestParam String about, Map<String, Object> model) {
        logger.debug(link + " " + mainInfo + " " + about);
        Post post = new Post(link, mainInfo, about);
        postRepository.save(post);
        return "posting-page";
    }

    @GetMapping("/add-post-page")
    public String createNewPost() {
        return "add-new-post";
    }

    @GetMapping("/main")
    public String main() {
        return "main";
    }

    @GetMapping("/readMore")
    public String readMore() {
        return "readMore";
    }

}
