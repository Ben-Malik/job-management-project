package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.exceptions.JobNotFoundException;
import com.example.jobmanagementproject.models.Job;
import com.example.jobmanagementproject.repositories.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
        return jobRepository.save(newJob);
    }

    @Override
    public List<Job> getByState(State state) {
        return jobRepository.getAllByState(state);
    }
}
