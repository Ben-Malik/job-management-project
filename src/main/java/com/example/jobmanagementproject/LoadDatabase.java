package com.example.jobmanagementproject;

import com.example.jobmanagementproject.enums.State;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import com.example.jobmanagementproject.repositories.JobRepository;
import com.example.jobmanagementproject.models.Job;


@Configuration
@EntityScan(basePackages = "com.example.jobmanagementproject.models")
@EnableJpaRepositories(basePackages = "com.example.jobmanagementproject.repositories")
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(JobRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Job(1L, "Bilbo Baggins", State.QUEUED)));
            log.info("Preloading " + repository.save(new Job(1L, "Frodo Baggins", State.COMPLETED)));

        };
    }
}
