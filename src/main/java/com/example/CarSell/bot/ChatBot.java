package com.example.CarSell.bot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ChatBot {

    private List<String> greetings;
    private List<String> asking;
    private List<String> negative;

    public ChatBot() {
        greetings = new ArrayList<>(Arrays.asList("hello", "hi", "good morning",
                                                  "good afternoon", "good evening"));
        asking = new ArrayList<>(Arrays.asList("how", "what", "why", "which"));
        negative = new ArrayList<>(Arrays.asList("fuck", "bitch", "moran", "ass", "asshole", "fucking"));

    }

    private MessageType detectMessageType(String message) {
        if(negative.contains(message)) {
            return MessageType.NEGATIVE;
        } else if(greetings.contains(message)) {
            return MessageType.GREETING;
        } else if(asking.contains(message)) {
            return MessageType.ASK;
        } else {
            return MessageType.UNCLEAR;
        }
    }

    public String generateMessage(String message) {
        MessageType type = detectMessageType(message.toLowerCase());
        if(type == MessageType.NEGATIVE) {
            return "Please, don't use bad words(((";
        }
        if(type == MessageType.UNCLEAR) {
            return "Sorry, I am poor AI, I can't understand you";
        }

        if(type == MessageType.GREETING) {
            return generateGreeting();
        } else {
            return "Sorry, i can't answer to question yet";
        }
    }

    private String generateGreeting() {
        Random random = new Random();
        int randomGreeting = random.nextInt(greetings.size() - 1);
        return greetings.get(randomGreeting) + "!";
    }

}

enum MessageType {
    GREETING, ASK, NEGATIVE, UNCLEAR
}

