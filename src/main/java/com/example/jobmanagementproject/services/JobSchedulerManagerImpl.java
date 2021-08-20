package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.models.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * An implementation of the {@link JobSchedulerManager} interface.
 *
 * @author ben-maliktchamalam
 */

@Component
public class JobSchedulerManagerImpl implements JobSchedulerManager{

    @Autowired
    JobManager jobManager;

    private ThreadPoolTaskScheduler jobScheduler = new ThreadPoolTaskScheduler();

    private ExecutorService priorityJobPoolExecutor;

    private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();

    private PriorityBlockingQueue<Job> priorityQueue;

    @Override
    public void scheduleJob(Job job) {
        jobScheduler.schedule(job, new Date());
    }

    @Override
    public List<Job> getRunningJobs() {
        return null;
    }

    @Override
    @PostConstruct
    public void enqueueJobs() {
        int poolSize = 10;
        int queueSize = 20;

        priorityJobPoolExecutor = Executors.newFixedThreadPool(poolSize);
        priorityQueue = new PriorityBlockingQueue<>(queueSize,
                Comparator.comparing(Job::getPriority));

        populatePriorityQueue();

        priorityJobScheduler.execute(()->{
            while (true) {
                try {
                    Job currentJob = priorityQueue.take();
                    priorityJobPoolExecutor.execute(currentJob);
                    if(currentJob.getState().equals(State.RUNNING))
                        currentJob.setState(State.DONE);
                    jobManager.create(currentJob);
                } catch (InterruptedException e) {
                    // exception needs special handling
                    break;
                }
            }
        });
    }

    @Override
    public void enqueueJob(Job job) {
        job.setState(State.QUEUED);
        priorityQueue.add(job);
    }

    @Override
    public void scheduleByDate(Job job, Date date) {
        jobScheduler.schedule(job, date);
    }

    @Override
    public void scheduleByCron(Job job) {
        jobScheduler.schedule(job, new CronTrigger(job.getCronRunTime()));
    }

    public int getQueuedTaskCount() {
        return priorityQueue.size();
    }

    protected void close(ExecutorService scheduler) {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    public void closeScheduler() {
        close(priorityJobPoolExecutor);
        close(priorityJobScheduler);
    }

    private void populatePriorityQueue() {
        Logger logger = Logger.getLogger(JobSchedulerManagerImpl.class.getName());
        List<Job> jobs = jobManager.getByState(State.QUEUED);
        for (Job job: jobs) {
            enqueueJob(job);
            logger.info(job.toString());
        }
    }

}
