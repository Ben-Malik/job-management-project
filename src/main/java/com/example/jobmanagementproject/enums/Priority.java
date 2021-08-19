package com.example.jobmanagementproject.enums;

/**
 * An enumeration for the priority of a particular job.
 * @author ben-maliktchamalam
 */
public enum Priority {
    LOW(0),
    MIDDLE(1),
    HIGH(2);

    private int priority;

    Priority(int priority) {
        this.priority = priority;
    }
}
