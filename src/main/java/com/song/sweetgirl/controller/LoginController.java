package com.song.sweetgirl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class LoginController {

    @RequestMapping(value = "login")
    public String login(){
        return "index";
    }

}
