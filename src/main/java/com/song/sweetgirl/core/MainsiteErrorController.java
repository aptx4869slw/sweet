package com.song.sweetgirl.core;

import com.song.sweetgirl.controller.LoginController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainsiteErrorController implements ErrorController {

    private final Logger logger = LoggerFactory.getLogger(MainsiteErrorController.class);

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        logger.debug("statusCode:" + statusCode);
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
