package com.curioushead.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GreetingServiceTest {
    private String test = new GreetingService().greet("Hello, Madhur!");

    @Test
    void greet() {
        assertEquals("Hello, Madhur!", test);
    }
}