package com.example.jobmanagementproject.repositories;

import com.example.jobmanagementproject.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * A repository interface indicating all possible actions doable on the {@link Job} class.
 *
 * @author ben-maliktchamalam
 */
public interface JobRepository  extends JpaRepository<Job, Long> {

}
