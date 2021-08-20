package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.models.Job;

import java.util.Date;
import java.util.List;

public interface JobSchedulerManager {

    void scheduleJob(Job job);

    List<Job> getRunningJobs();

    void enqueueJobs();

    void enqueueJob(Job job);

    void scheduleByDate(Job job, Date date);

    void scheduleByCron(Job job);




}
