package com.song.sweetgirl.controller;

import com.song.sweetgirl.model.User;
import com.song.sweetgirl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<String> login(HttpServletRequest request, HttpServletResponse response, @Valid User user) {
        logger.debug("REST request to Login Into System :{}", user.toString());
        try {
            String result = userService.login(request, response, user);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to Login Into System :{}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("error.html");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(HttpServletRequest request, HttpServletResponse response, @Valid User user) {
        logger.debug("REST request to register User Into System :{}", user.toString());
        try {
            String result = userService.register(request, response, user);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to register User Into System :{}" + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("error.html");
        }
    }

    @GetMapping(value = "/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("REST request to get captcha from System :{}", request.getPathInfo());
        try {
            userService.captcha(request, response);
        } catch (Exception e) {
            logger.error("REST request to get captcha from System :{}" + e.getMessage());
        }
    }

}
