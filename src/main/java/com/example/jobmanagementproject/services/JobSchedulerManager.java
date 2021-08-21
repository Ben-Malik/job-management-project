package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.models.Job;

import java.util.Date;
import java.util.List;

public interface JobSchedulerManager {

    Job runJobNow(Job job);

    List<Job> getRunningJobs();

    List<Job> getFailedJobs();

    List<Job> getCompletedJobs();

    List<Job> getQueuedJobs();

    void enqueueJobs();

    void enqueueJob(Job job);

    void scheduleByDate(Job job, Date date);

    void scheduleByCron(Job job);

    void runCronJobs();

    List<Job> runJobsNow(List<Job> jobs);

}
