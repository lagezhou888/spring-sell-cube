package com.cgz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cgz.*.mapper")
public class SpringbootSellCubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSellCubeApplication.class, args);
	}

}