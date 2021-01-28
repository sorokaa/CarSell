package com.example.CarSell.controller;

import com.example.CarSell.POJO.GenerateHtml;
import com.example.CarSell.domain.Post;
import com.example.CarSell.repository.PostRepository;
import org.dom4j.rule.Mode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    /*
    * Add new post to database
     */
    @PostMapping("add")
    public String add(@RequestParam String link,
                      @RequestParam String mark,
                      @RequestParam String modelCar,
                      @RequestParam String color,
                      @RequestParam String volume,
                      @RequestParam String cost,
                      @RequestParam String bodyType,
                      @RequestParam String shortInfo,
                      Map<String, Object> model) {

        Double engineV = null;

        try {
            engineV = Double.parseDouble(volume);
        } catch (NumberFormatException e) {
            volume = volume.replace(",", ".");
            engineV = Double.parseDouble(volume);
        }

        Integer costToInteger = Integer.parseInt(cost);
        Post post = new Post(link, mark, modelCar, engineV, costToInteger, color, bodyType, shortInfo);
        postRepository.save(post);

        GenerateHtml.generatePage(post);

        return "posting-page";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Post> posts;

        if(filter != null && !filter.isEmpty()) {
            /*
                Capitalize model of car
             */
            filter = filter.toLowerCase();
            filter = filter.replaceFirst(filter.substring(0,1), filter.substring(0,1).toUpperCase());

            posts = postRepository.findByMark(filter);

            if(((Collection<Post>)posts).size() == 0) {
                model.put("empty", "Can't find " + "\"" + filter + "\". Try another mark");
            }
        } else {
            posts = postRepository.findAll();
        }
        model.put("cars", posts);
        return "posting-page";
    }

    @GetMapping("/showCar/{id}")
    public String showCar(@PathVariable("id") Long id, Map<String, Object> model) {

        logger.info("Try to show a car with id" + id);
        Optional<Post> post = postRepository.findById(id);

        if(post.isEmpty()) {
            logger.error("Can't find car with id " + id);
        }

        model.put("car", post.get());

        return "post/" + post.get().getMark() + "#"
                        + post.get().getModel() + "#"
                        + id + ".html";
    }

    @GetMapping("showCar")
    public ModelAndView show(@RequestParam String id) {
        logger.info("Try open " + id + " post");
        return new ModelAndView("redirect:/showCar/" + id);
    }

    @PostMapping("/deletePost")
    public String deletePost(@RequestParam String id, Map<String, Object> model) {
        Optional<Post> post = postRepository.findById(Long.valueOf(id));

        if(!GenerateHtml.deletePage(post.get())) {
            model.put("error", "Can't remove car"); //TODO
            return "posting-page";
        }
        postRepository.deleteById(Long.valueOf(id));
        Iterable<Post> cars = postRepository.findAll();
        model.put("cars", cars);
        return "posting-page";
    }
}
