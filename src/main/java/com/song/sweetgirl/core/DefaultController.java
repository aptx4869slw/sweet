package com.song.sweetgirl.core;

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

    @RequestMapping(value = "/search")
    public String search() {
        return "blog/search";
    }

    @RequestMapping(value = "/detail")
    public String detail() {
        return "blog/detail";
    }

}
