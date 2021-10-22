package com.test.db.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc//Enabling Spring MVC  //@EnableWebMvc <- (equivalent to <mvc:annotation-driven />)
@ComponentScan(basePackages = {"com.test.db"})//Enabling component scanning
public class DBConfig implements WebMvcConfigurer{
	
	@Bean
	public DataSource getDataSource() {
		BasicDataSource dataSrc = new BasicDataSource();
//		dataSrc.setDriverClassName("org.hsqldb.jdbc.JDBCDriver");
//		dataSrc.setUrl("jdbc:hsqldb:hsql://localhost/");
//		dataSrc.setUsername("SA");
//		dataSrc.setPassword("");
		dataSrc.setDriverClassName("oracle.jdbc.OracleDriver");
		dataSrc.setUrl("jdbc:oracle:thin:@10.10.9.130:1521:twasl");
		dataSrc.setUsername("commontable4");
		dataSrc.setPassword("NB5QS");
		dataSrc.setMaxTotal(1000);
		dataSrc.setMaxIdle(1);
		dataSrc.setMaxConnLifetimeMillis(40000);
		dataSrc.setDefaultAutoCommit(false);
		dataSrc.setDefaultReadOnly(true);
		return dataSrc;
	}
	
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver("/WEB-INF/view/", ".jsp");
		return viewResolver;
	}
	
	@Bean("jdbcTemplate")
	public JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(getDataSource());
	}
	
	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");  
    }
	
	@Bean("txManager")
	public PlatformTransactionManager getTransactionManager() {
		return new DataSourceTransactionManager(getDataSource());
	}
	
	@Bean("transactionDefinition")
	public TransactionDefinition getTransactionDefinition(){
		return new DefaultTransactionDefinition();
	}

}
