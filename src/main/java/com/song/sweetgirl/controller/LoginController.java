package com.song.sweetgirl.controller;

import com.song.sweetgirl.model.User;
import com.song.sweetgirl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @PostMapping(value = "/login")
    public String login(User user) {
        logger.debug("REST request to Login Into System :{}", user.toString());
        try {
            return userService.login(user);
        } catch (Exception e) {
            logger.error("REST request to Login Into System :{}" + e.getMessage());
            return "error.html";
        }
    }

    @PostMapping(value = "/register")
    public String register(User user) {
        logger.debug("REST request to register User Into System :{}", user.toString());
        try {
            return userService.register(user);
        } catch (Exception e) {
            logger.error("REST request to register User Into System :{}" + e.getMessage());
            return "error.html";
        }
    }

}
