package com.song.sweetgirl;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.song.sweetgirl.dao")
public class SweetgirlApplication {

	public static void main(String[] args) {
		SpringApplication.run(SweetgirlApplication.class, args);
	}

}
