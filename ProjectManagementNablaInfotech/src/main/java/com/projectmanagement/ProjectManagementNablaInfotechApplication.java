package com.projectmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EnableEurekaClient
@EnableMongoRepositories("com.projectmanagement")
public class ProjectManagementNablaInfotechApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectManagementNablaInfotechApplication.class, args);
	}

}
