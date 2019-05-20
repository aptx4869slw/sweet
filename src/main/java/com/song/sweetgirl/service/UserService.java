package com.song.sweetgirl.service;

import com.song.sweetgirl.dao.UserDAO;
import com.song.sweetgirl.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.ServerException;

@Service
@Transactional
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private UserDAO userDAO;

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    public Boolean login(User user) throws Exception{
        User result = userDAO.findUser(user);
        if (result != null) {
            if (result.getUsername().equals(user.getUsername()) && result.getPassword().equals(user.getPassword())) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } else {
            return Boolean.FALSE;
        }
    }
}
