package com.scrum.workitem.jdbc;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement(proxyTargetClass=true)
public class ScrumWorkItemJdbcServiceConfiguration {

	public static final ExecutorService SCRUM_WORKITEM_EXECUTOR = Executors.newFixedThreadPool(25);
}
