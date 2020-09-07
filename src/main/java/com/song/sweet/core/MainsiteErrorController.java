package com.song.sweet.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Liwen
 * @Description // 项目异常控制, 跳转404页面
 * @Version 1.0.0
 * @create 2019-05-20 16:29
 */
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
