package com.song.sweetgirl.service;

import com.song.sweetgirl.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    public Boolean login(User user) {
        String username = "admin";
        String password = "123456";
        if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
