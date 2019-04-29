package com.song.sweetgirl.service;

import com.song.sweetgirl.service.dto.TestDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(TestService.class);

    public TestDTO save(TestDTO testDTO) {
        TestDTO result = new TestDTO();
        return result;
    }

    public void getOne() {

    }

    public void getAll() {

    }

    public void delete() {

    }

    public void update() {

    }

}
