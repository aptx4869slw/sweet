package com.song.sweet.scheduler;

import com.song.sweet.service.TestService;
import com.song.sweet.service.utils.GeneratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@EnableScheduling
public class TestScheduler {

    private final Logger logger = LoggerFactory.getLogger(TestScheduler.class);

    private final TestService testService;

    public TestScheduler(TestService testService) {
        this.testService = testService;
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void timer() {
        logger.info("Test Timer in progress! : {} ", GeneratorUtils.commonDateDTF.format(LocalDateTime.now()));
        try {
            testService.testTimer();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

}
