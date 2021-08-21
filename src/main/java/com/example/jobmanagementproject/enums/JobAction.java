package com.example.jobmanagementproject.enums;

import com.example.jobmanagementproject.models.Job;

/**
 * An enumeration for the {@link Job} type.
 * <br> <br>
 * @author ben-maliktchamalam
 */
public enum JobAction {

    NOTHING(0),
    SEND_EMAIL(1),
    DATA_LOAD(2),
    INDEXING(3),
    DISPLAY_MESSAGE(4);

    private int jobAction;

    JobAction(int jobAction) {
        this.jobAction = jobAction;
    }
}
