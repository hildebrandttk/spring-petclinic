package org.springframework.samples.petclinic.service;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration
@EnableJpaRepositories(basePackages = "org.springframework.samples.petclinic")
public class ClinicServiceTcBeanTestsConfiguration {

	private final MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7");

	public ClinicServiceTcBeanTestsConfiguration() {
		mySQLContainer.start();
	}

	@Bean
	MySQLContainer mySQLContainer() {
		return mySQLContainer;
	}

	@Bean
	DataSource dataSource(MySQLContainer mySQLContainer) {
		return DataSourceBuilder.create().url(mySQLContainer.getJdbcUrl()).password(mySQLContainer.getPassword())
				.username(mySQLContainer.getUsername()).build();
	}

}
