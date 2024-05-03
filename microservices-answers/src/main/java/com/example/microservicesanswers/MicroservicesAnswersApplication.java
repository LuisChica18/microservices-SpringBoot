package com.example.microservicesanswers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.example.microservicesanswers.entity",
        "com.example.commonsdomainassestment.entity",
        "com.example.commonsdomain.entity"})
public class MicroservicesAnswersApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesAnswersApplication.class, args);
    }

}
