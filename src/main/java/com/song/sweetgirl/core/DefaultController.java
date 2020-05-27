package com.song.sweetgirl.core;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

    @RequestMapping("/")
    public String blog() {
        return "blog/blog";
    }

    @RequestMapping(value = "/search.html")
    public String search() {
        return "blog/search";
    }

    @RequestMapping(value = "/detail.html")
    public String detail() {
        return "blog/detail";
    }

    @RequestMapping(value = "/album.html")
    public String album() {
        return "blog/album";
    }

    @RequestMapping(value = "/contact.html")
    public String contact() {
        return "blog/contact";
    }

    @RequestMapping(value = "/journal.html")
    public String journal() {
        return "blog/journal";
    }

    @RequestMapping(value = "/leave_word.html")
    public String leave_word() {
        return "blog/leave_word";
    }

    @RequestMapping(value = "/login.html")
    public String login() {
        return "login";
    }

}
