package com.scrum.workitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScrumWorkItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumWorkItemApplication.class, args);
    }

}

