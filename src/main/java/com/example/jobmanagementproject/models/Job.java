package com.example.jobmanagementproject.models;

import com.example.jobmanagementproject.enums.JobAction;
import com.example.jobmanagementproject.enums.Priority;
import com.example.jobmanagementproject.enums.State;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import com.google.gson.Gson;


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

    private String cronRunTime = "";

    @Override
    public String toString() {
        return "Job[ title: " + title + " Priority:" + priority.toString() + ", State:" + state.toString() + " ]";
    }

    @Override
    public void run() {

        Logger logger = Logger.getLogger(Job.class.getName());
        state = State.RUNNING;
        try {
            state = State.DONE;
            System.out.println("Runnable Task with " + title + " on thread " + Thread.currentThread().getName() + new Date() + this) ;
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.log(new LogRecord(Level.FINE, e.getMessage()));
            state = State.FAILED;
        }

    }
}
