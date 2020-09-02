package com.song.sweet.mapper;

import com.github.pagehelper.Page;
import com.song.sweet.model.Test;
import org.springframework.stereotype.Repository;

public interface TestMapper {

    Integer save(Test test);

    Test findOne(Long id);

    Page<Test> findAll();

    Page<Test> findAllByPage();

    Integer update(Test test);

    Integer delete(Long id);

    Integer countTests();

    Test findFirstTest();

}
