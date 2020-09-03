package com.song.sweet.controller;

import com.song.sweet.model.User;
import com.song.sweet.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Api(tags = "用户登录管理")
@RestController
@RequestMapping("/api")
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "/login")
    public ResponseEntity<User> login(HttpServletRequest request, HttpServletResponse response, @Valid User user) {
        logger.debug("REST request to Login Into System : {} ", user.toString());
        try {
            User result = userService.login(request, response, user);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to Login Into System : {} " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public ResponseEntity<String> register(HttpServletRequest request, HttpServletResponse response, @Valid User user) {
        logger.debug("REST request to register User Into System : {} ", user.toString());
        try {
            String result = userService.register(request, response, user);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        } catch (Exception e) {
            logger.error("REST request to register User Into System : {} " + e.getMessage());
            return ResponseEntity.status(HttpStatus.OK).body("error.html");
        }
    }

    @ApiOperation(value = "生成验证码", notes = "暂未使用，生成4位验证码图片")
    @GetMapping(value = "/captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) {
        logger.debug("REST request to get captcha from System : {} ", request.getPathInfo());
        try {
            userService.captcha(request, response);
        } catch (Exception e) {
            logger.error("REST request to get captcha from System : {} " + e.getMessage());
        }
    }

}
