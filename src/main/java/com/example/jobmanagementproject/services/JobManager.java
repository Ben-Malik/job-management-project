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

    /**
     * Get all jobs available in the db.
     * @return a list of {@link Job}s
     */
    List<Job> getJobs();

    /**
     * Get the {@link Job} having given id.
     * @param id The id of the job sought.
     * @return A job object.
     * @throws JobNotFoundException in case there is no job available with given id.
     */
    Job get(Long id) throws JobNotFoundException;

    /**
     * Deletes the job with given id.
     * @param id Theid of the job to be deleted.
     */
    void delete(Long id);

    /**
     * Updates a job with the given new attributes.
     * @param newJob The new job to be saved
     * @param id the id of the job
     * @return The newly saved job.
     */
    Job update(Job newJob, Long id);

    /**
     * Creates a {@link Job}
     * @param newJob The job to be created
     * @return The newly created job.
     */
    Job create(Job newJob);

    /**
     * Grabs all jobs matching the given job {@link State}
     * @param state The type of state of jobs looked for.
     * @return A list of jobs
     */
    List<Job> getByState(State state);

    /**
     * Grabs jobs that are currently in the queue.
     * @return a list of jobs with state queued
     */
    List<Job> getQueuedJobs();

    /**
     * Grabs jobs currently running.
     * @return a list of job.
     */
    List<Job> getRunningJobs();

    /**
     * Grabs jobs having failed.
     * @return  a list of job.
     */
    List<Job> getFailedJobs();

    /**
     * Grabs jobs having completed.
     * @return a list of job.
     */
    List<Job> getCompletedJobs();

    List<Job> getJobsWithCron();

}
