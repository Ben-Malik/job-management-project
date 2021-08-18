package com.example.jobmanagementproject.controllers;


import java.util.List;

import com.example.jobmanagementproject.exceptions.JobNotFoundException;
import com.example.jobmanagementproject.models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.jobmanagementproject.repositories.JobRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobRepository repository;

    @RequestMapping(value = "/jobs", method = RequestMethod.GET,  produces = {"application/json"})
    public @ResponseBody List<Job> all() {
        return repository.findAll();
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public @ResponseBody Job createJob(@RequestBody Job newJob) {
        return repository.save(newJob);
    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public @ResponseBody Job getJob(@PathVariable Long id) throws JobNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));

    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public @ResponseBody Job editJob(@RequestBody Job newJob, @PathVariable Long id) {

        return repository.findById(id)
                .map(job -> {
                    newJob.setTitle(newJob.getTitle());
                    newJob.setState(newJob.getState());
                    return repository.save(job);
                })
                .orElseGet(() -> {
                    newJob.setId(id);
                    return repository.save(newJob);
                });
    }

    @DeleteMapping("/jobs/{id}")
    void deleteJob(@PathVariable Long id) {
        repository.deleteById(id);
    }

}
