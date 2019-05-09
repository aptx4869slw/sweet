package com.song.sweetgirl.scheduler;

import com.song.sweetgirl.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class TestScheduler {

    private final Logger logger = LoggerFactory.getLogger(TestScheduler.class);

    @Autowired
    private TestService testService;

    @Scheduled(fixedRate = 5000)
    public void timer() {
        logger.debug("Timer in progress! :{}", LocalDateTime.now());
        try {
            testService.testTimer();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
