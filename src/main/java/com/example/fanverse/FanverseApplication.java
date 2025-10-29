package com.example.fanverse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.fanverse.mapper")
public class FanverseApplication {

	public static void main(String[] args) {
		SpringApplication.run(FanverseApplication.class, args);
	}

}
