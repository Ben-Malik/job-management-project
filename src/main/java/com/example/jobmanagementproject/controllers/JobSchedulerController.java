package com.example.jobmanagementproject.controllers;

import com.example.jobmanagementproject.services.JobSchedulerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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
    public ModelAndView greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);

        ModelAndView mav = new ModelAndView("home");
        mav.addObject("name", "Ben");

        return mav;
    }

}
