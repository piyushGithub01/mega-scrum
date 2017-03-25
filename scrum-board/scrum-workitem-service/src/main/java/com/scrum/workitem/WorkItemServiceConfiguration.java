package com.scrum.workitem;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.scrum.persistenceapi.PersistenceApiConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({ PersistenceApiConfiguration.class })
public class WorkItemServiceConfiguration {

	public static final ExecutorService WORKITEM_EXECUTOR = Executors.newFixedThreadPool(25);
}
