package com.scrum.workitem.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ScrumWorkItemHibernateApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScrumWorkItemHibernateApplication.class, args);
    }

}

