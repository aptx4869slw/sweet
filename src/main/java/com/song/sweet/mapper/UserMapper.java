package com.song.sweet.mapper;

import com.song.sweet.model.User;
import org.springframework.stereotype.Repository;

public interface UserMapper {

    Integer saveUser(User user);

    User findUser(User user);

    User findByUserName(User user);

}
