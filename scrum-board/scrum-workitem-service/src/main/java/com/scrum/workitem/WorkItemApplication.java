package com.scrum.workitem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class WorkItemApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkItemApplication.class, args);
    }

}

