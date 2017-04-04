package com.scrum.orchestration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScrumOrchestartionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumOrchestartionApplication.class, args);
    }

}

