package com.song.sweet.scheduler;

import com.song.sweet.service.utils.GeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class Scheduler {

    private final Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Scheduled(fixedRate = 1000 * 60 * 30)
    public void timer() {
        try {
            logger.info("Timer in progress! : {} ", GeneratorUtils.commonDateDTF.format(LocalDateTime.now()));
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
