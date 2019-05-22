package com.song.sweetgirl.service;

import com.song.sweetgirl.dao.UserDAO;
import com.song.sweetgirl.model.User;
import com.song.sweetgirl.model.enumeration.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    @Autowired
    private UserDAO userDAO;

    private final String BLOG_MAIN_PAGE = "blog";

    /**
     * 登陆
     *
     * @param user
     * @return
     */
    public String login(User user) throws Exception {
        User result = userDAO.findUser(user);
        if (result != null) {
            if (result.getUsername().equals(user.getUsername()) && result.getPassword().equals(user.getPassword())) {
                logger.info(result.getUsername() + UserStatus.LOGIN_SUCCESS.description());
                return BLOG_MAIN_PAGE;
            }
        }
        return UserStatus.LOGIN_FAILED.description();
    }

    public String register(User user) throws Exception {
        User result = userDAO.findByUserName(user);
        if (result != null) {
            return UserStatus.USER_EXIST.description();
        } else {
            Integer num = userDAO.saveUser(user);
            if (num > 0) {
                return UserStatus.USER_REGISTER_SUCCESS.description();
            }
            return UserStatus.USER_REGISTER_ERROR.description();
        }
    }
}
