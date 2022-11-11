package com.curioushead.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class GoodByeController {

    private final GoodByeService bye;

    public GoodByeController(GoodByeService bye) {
        this.bye = bye;
    }

    @RequestMapping(path = "/goodbye", method = RequestMethod.GET)
    public @ResponseBody String greeting() {
        return bye.goodbye("Madhur");
    }

}