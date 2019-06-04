package com.song.sweetgirl.scheduler;

import com.song.sweetgirl.service.BarrageService;
import com.song.sweetgirl.service.TestService;
import com.song.sweetgirl.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private TestService testService;

    @Autowired
    private UserService userService;

    @Autowired
    private BarrageService barrageService;

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void timer() {
        logger.debug("Timer in progress! :{}", LocalDateTime.now());
        try {

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
