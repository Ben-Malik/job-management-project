package com.example.jobmanagementproject.controllers;


import java.util.List;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.exceptions.JobNotFoundException;
import com.example.jobmanagementproject.models.Job;
import com.example.jobmanagementproject.services.JobManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    @Autowired
    private JobManager jobManager;

    @RequestMapping(value = "/jobs", method = RequestMethod.GET,  produces = {"application/json"})
    public @ResponseBody List<Job> all() {
        return jobManager.getJobs();
    }

    @RequestMapping(value = "/jobs", method = RequestMethod.POST)
    public @ResponseBody Job createJob(@RequestBody Job newJob) {
        return jobManager.create(newJob);
    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.GET)
    public @ResponseBody Job getJob(@PathVariable Long id) throws JobNotFoundException {

       try {

           return jobManager.get(id);

       } catch (JobNotFoundException e) {
          System.out.println(e.getMessage());
       }
       return null; //TODO return model and view here.
    }

    @RequestMapping(value = "/jobs/{id}", method = RequestMethod.PUT)
    public @ResponseBody Job editJob(@RequestBody Job newJob, @PathVariable Long id) {
        return jobManager.update(newJob, id);
    }

    @DeleteMapping("/jobs/{id}")
    void deleteJob(@PathVariable Long id) {
        jobManager.delete(id);
    }

    @RequestMapping(value = "job/{state}", method = RequestMethod.GET)
    public @ResponseBody List<Job> getByState(Integer state) {
        return jobManager.getByState(State.QUEUED);
    }
}
