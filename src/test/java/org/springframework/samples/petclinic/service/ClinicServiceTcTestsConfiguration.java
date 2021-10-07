package org.springframework.samples.petclinic.service;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.testcontainers.containers.MySQLContainer;

@TestConfiguration
@EnableJpaRepositories(basePackages = "org.springframework.samples.petclinic")
public class ClinicServiceTcTestsConfiguration {

	@Bean
	DataSource dataSource() {
		return DataSourceBuilder.create()
			.url(ClinicServiceTcTests.MYSQLCONTAINER.getJdbcUrl())
			.password(ClinicServiceTcTests.MYSQLCONTAINER.getPassword())
			.username(ClinicServiceTcTests.MYSQLCONTAINER.getUsername()).build();
	}
}
