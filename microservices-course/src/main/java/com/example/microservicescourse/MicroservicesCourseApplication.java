package com.example.microservicescourse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.example.commonsdomain.entity",
			"com.example.microservicescourse.entity",
			"com.example.commonsdomainassestment.entity"})
public class MicroservicesCourseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroservicesCourseApplication.class, args);
	}

}
