package com.song.sweetgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String login() {
        return "index";
    }


    @RequestMapping(value = "/blog")
    public String blog() {
        return "blog/index";
    }

}
