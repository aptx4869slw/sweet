package com.song.sweet.core;

import com.song.sweet.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WebController {

    private final WebService webService;

    public WebController(WebService webService) {
        this.webService = webService;
    }

    @RequestMapping("/")
    public String blog(HttpServletRequest request) {
        try {
            webService.getProvinceByIP(request);
        } catch (Exception e) {
            return "blog/blog";
        }
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
