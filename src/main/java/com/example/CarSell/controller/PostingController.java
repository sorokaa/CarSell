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
public class PostingController {


    @Autowired
    private PostRepository postRepository;

    private Logger logger = LoggerFactory.getLogger(PostingController.class);

    @GetMapping("/posting-page")
    public String carsPage(Map<String, Object> model) {
        Iterable<Post> all = postRepository.findAll();
        model.put("cars", all);
        return "posting-page";
    }

    @PostMapping("add")
    public String add(@RequestParam String link, @RequestParam String mainInfo, @RequestParam String about, Map<String, Object> model) {
        Post post = new Post(link, mainInfo, about);
        postRepository.save(post);
        return "posting-page";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Post> posts;
        if(filter != null && !filter.isEmpty()) {
            posts = postRepository.findByMainInfoStartsWith(filter);
        } else {
            posts = postRepository.findAll();
        }
        model.put("cars", posts);
        return "posting-page";
    }


    //For admin
    @PostMapping("/deletePost")
    public String deletePost(@RequestParam String mainInfo, Map<String, Object> model) {
        logger.info(mainInfo);

        if(mainInfo != null && !mainInfo.isEmpty()) {
            postRepository.deleteByMainInfo(mainInfo);
        }

        model.put("cars", postRepository.findAll());

        return "posting-page";
    }
}
