package com.example.jobmanagementproject.controllers;

import com.example.jobmanagementproject.services.JobSchedulerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JobSchedulerController {

    @Autowired
    private JobSchedulerManager schedulerManager;

    @RequestMapping(value = "/runQueuedJobs", method = RequestMethod.PUT)
    public @ResponseBody void executeByPriority() {
        schedulerManager.enqueueJobs();
    }

   @RequestMapping(value = "/job-manager", method = RequestMethod.GET)
    public ModelAndView displayJobs() {

        ModelAndView mav = new ModelAndView("home");

        mav.addObject("failed", schedulerManager.getFailedJobs());
        mav.addObject("completed", schedulerManager.getCompletedJobs());
        mav.addObject("queued", schedulerManager.getQueuedJobs());
        mav.addObject("running", schedulerManager.getRunningJobs());

        return mav;
    }

}
