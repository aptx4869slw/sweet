package com.song.sweetgirl.dao;

import com.song.sweetgirl.model.Test;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestDao {

    Integer save(Test test);

    Test findOne(Long id);

    List<Test> findAll();

    Integer update(Test test);

    Integer delete(Long id);

}
