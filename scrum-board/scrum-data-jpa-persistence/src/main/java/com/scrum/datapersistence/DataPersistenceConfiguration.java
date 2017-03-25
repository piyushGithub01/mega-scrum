package com.scrum.datapersistence;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "com.scrum.datapersistence.entity")
@ComponentScan
@EnableJpaRepositories(basePackages = "com.scrum.datapersistence.repository")
@EnableTransactionManagement
public class DataPersistenceConfiguration {
	
}
