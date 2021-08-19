package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.exceptions.JobNotFoundException;
import com.example.jobmanagementproject.models.Job;

import java.util.List;

/**
 * A manager interface defining operations doable on a {@link Job} object.
 * @author ben-maliktchamalam
 */
public interface JobManager {

    public List<Job> getJobs();

    public Job get(Long id) throws JobNotFoundException;

    public void delete(Long id);

    public Job update(Job newJob, Long id);

    public Job create(Job newJob);

    public List<Job> getByState(State state);

}
