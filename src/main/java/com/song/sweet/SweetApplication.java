package com.song.sweet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
//@EnableFeignClients
@ServletComponentScan
@SpringBootApplication
@MapperScan("com.song.sweet.dao")
public class SweetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweetApplication.class, args);
	}

}
