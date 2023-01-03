package com.curioushead.api;

import org.springframework.stereotype.Service;

@Service
public class GreetingService {
    public String greet(String madhur) {
        return "Hello, Madhur!";
    }
}