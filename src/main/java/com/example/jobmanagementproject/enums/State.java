package com.example.jobmanagementproject.enums;

/**
 * An enumeration indicating the current state of a particular Job.
 *
 * @author ben-maliktchamalam
 */
public enum State {
    QUEUED(0),
    RUNNING(1),
    DONE(2),
    FAILED(3);

    private final int stateOrdinal;

    State(int stateOrdinal) {
        this.stateOrdinal = stateOrdinal;
    }
}
