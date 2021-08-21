package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.exceptions.JobNotFoundException;
import com.example.jobmanagementproject.models.Job;
import com.example.jobmanagementproject.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * An implementation of the JobManager interface.
 *
 * @author ben-maliktchamalam
 */
@Service
@Transactional
public class JobManagerImpl implements JobManager{

    @Autowired
    private JobRepository jobRepository;

    @Override
    public List<Job> getJobs() {
        return jobRepository.findAll();
    }

    @Override
    public Job get(Long id) throws JobNotFoundException {
        return jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));
    }

    @Override
    public void delete(Long id) {
         jobRepository.deleteById(id);
    }

    @Override
    public Job update(Job newJob, Long id) {
        jobRepository.findById(id)
                .map(job -> {
                    job.setTitle(newJob.getTitle());
                    job.setState(newJob.getState());
                    return jobRepository.save(job);
                })
                .orElseGet(() -> {
                    newJob.setId(id);
                    return jobRepository.save(newJob);
                });
        return newJob;
    }

    @Override
    public Job create(Job newJob) {
        Job job = jobRepository.save(newJob);
        System.out.println(job);
        return job;
    }

    @Override
    public List<Job> getByState(State state) {
        return jobRepository.getAllByState(state);
    }

    @Override
    public List<Job> getQueuedJobs() {
        return getByState(State.QUEUED);
    }

    @Override
    public List<Job> getRunningJobs() {
        return getByState(State.RUNNING);
    }

    @Override
    public List<Job> getFailedJobs() {
        return getByState(State.FAILED);
    }

    @Override
    public List<Job> getCompletedJobs() {
        return getByState(State.DONE);
    }

    @Override
    public List<Job> getJobsWithCron() {
        return jobRepository.getJobsWithCron();
    }

}
