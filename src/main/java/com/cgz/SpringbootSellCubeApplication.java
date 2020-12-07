package com.cgz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@MapperScan("com.cgz.*.mapper")
@EnableEurekaClient
public class SpringbootSellCubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSellCubeApplication.class, args);
	}

}