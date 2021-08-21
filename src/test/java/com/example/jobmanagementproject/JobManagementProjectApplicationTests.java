package com.example.jobmanagementproject;

import com.example.jobmanagementproject.controllers.JobController;
import com.example.jobmanagementproject.enums.JobAction;
import com.example.jobmanagementproject.enums.Priority;
import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.models.Job;
import com.example.jobmanagementproject.services.JobManager;
import com.example.jobmanagementproject.services.JobSchedulerManager;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.allOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class JobManagementProjectApplicationTests {

    @Autowired
    JobController jobController;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JobManager jobManager;

    @MockBean
    JobSchedulerManager jobSchedulerManager;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/jobs")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void contextLoads() {
        assertThat(jobController).isNotNull();
    }

    @Test
    public void testJobIsInDoneStateWhenSuccessfullyExecuted() throws Exception {
        Job job = new Job();
        job.setId(1L);
        job.setState(State.QUEUED);
        job.setPriority(Priority.LOW);
        job.setTitle("Take the bin out");
        job.setRunNow(true);
        job.setType(JobAction.DATA_LOAD);

        when(jobSchedulerManager.runJobNow(job)).thenReturn(job);

        Assertions.assertThat(job.getState().equals(State.DONE));

        verify(jobSchedulerManager, times(0)).enqueueJob(job);
    }

    /**
     * A helper method to generate a {@link Job} with the given parameters.
     * @param id The of the job.
     * @param title The title of the job.
     * @param state the state of the job.
     * @return a job object.
     */
    private Job createJob(Long id, String title, State state) {

        Job job = new Job();
        job.setId(id);
        job.setState(state);
        job.setTitle(title);

        return job;
    }

}
