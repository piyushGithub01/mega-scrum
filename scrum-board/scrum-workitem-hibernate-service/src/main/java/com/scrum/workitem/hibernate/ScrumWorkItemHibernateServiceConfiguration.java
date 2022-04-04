package com.scrum.workitem.hibernate;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.scrum.persistenceapi.PersistenceApiConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@Import({ PersistenceApiConfiguration.class })
@EnableTransactionManagement(proxyTargetClass=true)
public class ScrumWorkItemHibernateServiceConfiguration {

	public static final ExecutorService SCRUM_WORKITEM_EXECUTOR = Executors.newFixedThreadPool(25);
}
