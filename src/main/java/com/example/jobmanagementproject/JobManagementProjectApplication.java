package com.example.jobmanagementproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@Configuration
@ComponentScan({"com.example.jobmanagementproject.controllers", "com.example.jobmanagementproject.repositories", "com.example.jobmanagementproject.services"})
@EntityScan("com.example.jobmanagementproject.models")
public class JobManagementProjectApplication {

    public static void main(String[] args) {

        System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(JobManagementProjectApplication.class, args);

    }

    @PostConstruct
    public void init(){
        // Setting Spring Boot SetTimeZone
        TimeZone.setDefault(TimeZone.getTimeZone("tr"));
    }

}
