package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk;

import com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.audit.AuditAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
@EnableJpaRepositories("com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.repository")
@EntityScan("com.mariomatejovic.masterSpringFramework.masterSpringFrameowk.model")

public class MasterSpringFrameowkApplication {

	public static void main(String[] args) {
		SpringApplication.run(MasterSpringFrameowkApplication.class, args);


	}
//
//	public DataSource myDataSource(){
//		DriverManagerDataSource ds = new DriverManagerDataSource();
//
//	}

}
