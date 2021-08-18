package com.example.jobmanagementproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repositories.JobRepository;
import models.Job;

@Configuration
@EntityScan(basePackages = "models")
@EnableJpaRepositories(basePackages = "repositories")
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(JobRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new Job(1L, "Bilbo Baggins", "burglar")));
            log.info("Preloading " + repository.save(new Job(1L, "Frodo Baggins", "thief")));

        };
    }
}
