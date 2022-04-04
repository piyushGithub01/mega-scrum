package com.scrum.workitem.hibernate.datasource;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Profile("test")
public class TestDataBaseConfiguration {

	@Bean
	public DataSource dataSource() {

		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		return builder.setType(EmbeddedDatabaseType.H2).build();
	}

    @Bean
	public LocalSessionFactoryBean entityManagerFactory() {

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setGenerateDdl(true);
		
		Properties p = new Properties();
		p.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
		p.setProperty("hibernate.current_session_context_class", "thread");
		p.setProperty("hibernate.show_sql", "true");
		
		LocalSessionFactoryBean factory = new org.springframework.orm.hibernate5.LocalSessionFactoryBean();
		factory.setPackagesToScan("com.scrum.workitem.hibernate.entity");
		factory.setDataSource(dataSource());
		factory.setHibernateProperties(p);
		return factory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {

		HibernateTransactionManager txManager = new org.springframework.orm.hibernate5.HibernateTransactionManager();
		txManager.setSessionFactory(entityManagerFactory().getObject());
		return txManager;
	}
}
