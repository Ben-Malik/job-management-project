package com.example.jobmanagementproject.exceptions;

public class JobNotFoundException extends Exception {
    public JobNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
