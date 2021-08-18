package com.example.jobmanagementproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.JobRepository;

@SpringBootApplication
@Configuration
public class JobManagementProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobManagementProjectApplication.class, args);
    }

}
