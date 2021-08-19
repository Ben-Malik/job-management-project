package com.example.jobmanagementproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@ComponentScan({"com.example.jobmanagementproject.controllers", "com.example.jobmanagementproject.repositories", "com.example.jobmanagementproject.services"})
public class JobManagementProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobManagementProjectApplication.class, args);
    }

}
