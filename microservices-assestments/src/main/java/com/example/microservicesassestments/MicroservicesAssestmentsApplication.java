package com.example.microservicesassestments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"com.example.commonsdomainassestment.entity"})
public class MicroservicesAssestmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroservicesAssestmentsApplication.class, args);
    }

}
