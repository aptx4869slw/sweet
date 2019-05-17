package com.song.sweetgirl.dao;

import com.song.sweetgirl.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    User findUser(User user);

}
