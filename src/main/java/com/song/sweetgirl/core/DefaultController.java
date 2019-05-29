package com.song.sweetgirl.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/blog")
    public String blog() {
        return "blog/blog";
    }

    @RequestMapping(value = "/search")
    public String search() {
        return "blog/search";
    }

    @RequestMapping(value = "/detail")
    public String detail() {
        return "blog/detail";
    }

    @RequestMapping(value = "/album")
    public String album() {
        return "blog/album";
    }

    @RequestMapping(value = "/contact")
    public String contact() {
        return "blog/contact";
    }

    @RequestMapping(value = "/journal")
    public String journal() {
        return "blog/journal";
    }

    @RequestMapping(value = "/leave_word")
    public String leave_word() {
        return "blog/leave_word";
    }

}
