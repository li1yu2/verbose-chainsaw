package com.eden;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.eden.dao")
public class ManageempApplication {

	public static void main(String[] args) {
		SpringApplication.run(ManageempApplication.class, args);
	}

}
