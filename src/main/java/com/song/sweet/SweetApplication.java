package com.song.sweet;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

//@ServletComponentScan
//@EnableFeignClients
@EnableCaching
@SpringBootApplication
@EnableEncryptableProperties()
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
