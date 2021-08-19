package com.example.jobmanagementproject.repositories;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * A repository interface indicating all possible actions doable on the {@link Job} class.
 *
 * @author ben-maliktchamalam
 */
@Repository
public interface JobRepository  extends JpaRepository<Job, Long> {

    /**
     * Grabs all jobs having their states as the given one.
     * @param state The state of the jobs sought.
     * @return A list of Jobs.
     */
    @Query("select j from Job j where j.state = :state")
    public List<Job> getAllByState(State state);

}
