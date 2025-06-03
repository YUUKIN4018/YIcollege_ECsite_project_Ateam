package com.college.yi.ecsite;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.college.yi.ecsite.admin.repository")
@SpringBootApplication
public class EcsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcsiteApplication.class, args);
	}

}
