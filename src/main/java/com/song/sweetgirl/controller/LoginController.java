package com.song.sweetgirl.controller;

import com.song.sweetgirl.model.User;
import com.song.sweetgirl.service.UserService;
import com.song.sweetgirl.service.dto.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.Map;

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
            Boolean result = userService.login(user);
            if (result) {
                return "blog";
            } else {
                return "error";
            }
        } catch (Exception e) {
            logger.error("REST request to Login Into System :{}" + e.getMessage());
            return "error.html";
        }
    }

}
