package com.example.gsaccessingdatamybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.gsaccessingdatamybatis.dao")
public class GsAccessingDataMybatisApplication {

	public static void main(String[] args) {
		SpringApplication.run(GsAccessingDataMybatisApplication.class, args);
	}
}
