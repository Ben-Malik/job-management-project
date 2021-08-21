package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.models.Job;

import java.util.Date;
import java.util.List;

/**
 * An interface for the JobScheduler Manager
 *
 * @author ben-maliktchamalam
 */
public interface JobSchedulerManager {

    /**
     * Executes the given job at the very time it's invoked.
     * @param job the job to be executed.
     * @return The job object with new state.
     */
    Job runJobNow(Job job);

    /**
     * Executes all jobs available in the db.
     * @return a list of jobs run.
     */
    List<Job> getRunningJobs();

    /**
     * Gets all jobs having failed as {@link com.example.jobmanagementproject.enums.State}.
     * @return a list jobs.
     */
    List<Job> getFailedJobs();

    /**
     * Gets jobs with Done as {@link State}
     * @return
     */
    List<Job> getCompletedJobs();

    /**
     * Gets jobs with Queued as {@link State}
     * @return
     */
    List<Job> getQueuedJobs();

    /**
     * Queues jobs not scheduled to the priority queue.
     */
    void enqueueJobs();

    /**
     * Adds a particular job into the  priority job queue.
     * @param job the job to be queued.
     */
    void enqueueJob(Job job);

    /**
     * Schedules a job by date.
     * @param job the job to be scheduled.
     * @param date the date at which the job is to be executed.
     */
    void scheduleByDate(Job job, Date date);

    /**
     * Schedules the given job with cron.
     * @param job the to be scheduled.
     */
    void scheduleByCron(Job job);

    /**
     * Executes all jobs in the db having cron run time.
     */
    void runCronJobs();

    /**
     * Executes the given jobs at the time of invoke
     * @param jobs the list of jobs to be executed.
     * @return a list of jobs with new states.
     */
    List<Job> runJobsNow(List<Job> jobs);

}
