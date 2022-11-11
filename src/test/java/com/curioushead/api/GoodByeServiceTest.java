package com.curioushead.api;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GoodByeServiceTest {
    private String test = new GoodByeService().goodbye("Bye, Richa!");

    @Test
    void goodBye() {
        assertEquals("Bye, Richa!", test);
    }
}