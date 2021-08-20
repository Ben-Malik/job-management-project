package com.example.jobmanagementproject.controllers;

import com.example.jobmanagementproject.services.JobSchedulerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobSchedulerController {

    @Autowired
    private JobSchedulerManager schedulerManager;

    @RequestMapping(value = "/runQueuedJobs", method = RequestMethod.PUT)
    public @ResponseBody void executeByPriority() {
        schedulerManager.enqueueJobs();
    }

}
