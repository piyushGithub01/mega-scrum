package com.scrum.workitem.jdbc.datasource;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@Profile("dev")
public class DevDataBaseConfiguration {

	@Value("${datasource.url}")
    private String databaseUrl;

    @Value("${datasource.username}")
    private String username;

    @Value("${datasource.password}")
    private String password;

    @Value("${datasource.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource(databaseUrl, username, password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }
    
    @Bean
    public JdbcTemplate getJdbcTemplate() {
        JdbcTemplate template = new JdbcTemplate(dataSource());
        return template;
    }
    
    @Bean
	public DataSourceTransactionManager transactionManager() {
    	DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}

}
