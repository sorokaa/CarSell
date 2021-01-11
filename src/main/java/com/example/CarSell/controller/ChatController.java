package com.example.CarSell.controller;

import com.example.CarSell.bot.ChatBot;
import com.example.CarSell.bot.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class ChatController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    private ChatBot bot = new ChatBot();

    private List<Message> listOfMessages = new ArrayList<>();

    @GetMapping("/help")
    public String pageLoad() {
        listOfMessages.clear();
        return "help";
    }


    @PostMapping("/chatMessage")
    public String getMessage(@RequestParam String message, Map<String, Object> model) {
        String answer = bot.generateMessage(message);

        Message messageObj = new Message(message, answer);
        listOfMessages.add(messageObj);

        model.put("botAnswer", listOfMessages);
        return "/help";
    }
}
