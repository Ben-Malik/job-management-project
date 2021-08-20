package com.example.jobmanagementproject.models;

import com.example.jobmanagementproject.enums.JobAction;
import com.example.jobmanagementproject.enums.Priority;
import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.services.JobManager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


/**
 * A class wrapping up all about a job.
 *
 * @author ben-maliktchamalam
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job implements Runnable {

    private @Id @GeneratedValue Long id;

    private String title;

    private Priority priority;

    private State state;

    private JobAction type;

    @Nullable
    private boolean runNow;

    @Nullable
    private String cronRunTime;

    @Override
    public String toString() {
        return "Job[ title: " + title + " Priority:" + priority.toString() + ", State:" + state.toString() + " ]";
    }

    @Override
    public void run() {
        JobManager jobManager;
        Logger logger = Logger.getLogger(Job.class.getName());
        state = State.RUNNING;
        try {
            System.out.println("Runnable Task with " + title + " on thread " + Thread.currentThread().getName());
            Thread.sleep(1000);
            state = State.DONE;

        } catch (InterruptedException e) {
            logger.log(new LogRecord(Level.FINE, e.getMessage()));
            state = State.FAILED;
        }

    }

}
