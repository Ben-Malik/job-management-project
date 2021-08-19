package com.example.jobmanagementproject.models;

import com.example.jobmanagementproject.enums.Priority;
import com.example.jobmanagementproject.enums.State;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * A class wrapping up all about a job.
 *
 * @author ben-maliktchamalam
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Job {

    private @Id @GeneratedValue Long id;

    private String title;

    private Priority priority = Priority.LOW;

    private State state;

    @Override
    public String toString() {
        return title + " " + state.toString();
    }

}
