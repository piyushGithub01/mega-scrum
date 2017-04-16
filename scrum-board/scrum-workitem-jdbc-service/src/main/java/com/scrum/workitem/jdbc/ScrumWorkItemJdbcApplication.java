package com.scrum.workitem.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScrumWorkItemJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumWorkItemJdbcApplication.class, args);
    }

}

