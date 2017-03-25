package com.scrum.persistenceapi;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.scrum.datapersistence.DataPersistenceConfiguration;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableTransactionManagement(proxyTargetClass=true)
@Import({ DataPersistenceConfiguration.class })
public class PersistenceApiConfiguration {

}
