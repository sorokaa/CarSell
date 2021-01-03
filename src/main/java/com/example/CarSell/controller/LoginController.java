package com.example.CarSell.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/success_login")
    public ModelAndView login_success() {
        logger.info("Successfuly login");
        return new ModelAndView("redirect:/main");
    }

}
