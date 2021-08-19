package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.models.Job;

import java.util.List;

public interface JobSchedulerManager {

    public void scheduleJob(Long id);

    List<Job> getRunningJobs();
}
