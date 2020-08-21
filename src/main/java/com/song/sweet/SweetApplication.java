package com.song.sweet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@ServletComponentScan
//@EnableFeignClients
//@EnableEncryptableProperties()
@EnableCaching
@SpringBootApplication
@MapperScan("com.song.sweet.dao")
public class SweetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SweetApplication.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

}
