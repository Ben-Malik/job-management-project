package controllers;


import java.util.List;

import com.JobNotFoundException;
import models.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import repositories.JobRepository;

@Controller
public class JobController {

    @Autowired
    private JobRepository repository;

    @GetMapping("/jobs")
    List<Job> all() {
        return repository.findAll();
    }

    @PostMapping("/jobs")
    Job createJob(@RequestBody Job newJob) {
        return repository.save(newJob);
    }

    @GetMapping("/jobs/{id}")
    Job getJob(@PathVariable Long id) throws JobNotFoundException {

        return repository.findById(id)
                .orElseThrow(() -> new JobNotFoundException(id));

    }

    @PutMapping("/jobs/{id}")
    Job editJob(@RequestBody Job newJob, @PathVariable Long id) {

        return repository.findById(id)
                .map(employee -> {
                    newJob.setTitle(newJob.getTitle());
                    newJob.setState(newJob.getState());
                    return repository.save(employee);
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
