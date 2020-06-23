package com.song.sweet.dao;

import com.song.sweet.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    Integer saveUser(User user);

    User findUser(User user);

    User findByUserName(User user);

}
