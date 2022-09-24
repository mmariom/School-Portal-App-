package com.mariomatejovic.masterSpringFramework.masterSpringFrameowk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@SpringBootApplication
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
