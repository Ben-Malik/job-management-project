package com.example.jobmanagementproject.services;

import com.example.jobmanagementproject.enums.State;
import com.example.jobmanagementproject.models.Job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
@Service
@EnableScheduling
public class JobSchedulerManagerImpl implements JobSchedulerManager{
    Logger logger = Logger.getLogger(JobSchedulerManagerImpl.class.getName());

    @Autowired
    JobManager jobManager;

    private final int POOL_SIZE = 10;

    private final int QUEUE_SIZE = 5;

    @Autowired
    private ThreadPoolTaskScheduler jobScheduler;

    private ExecutorService priorityJobPoolExecutor;

    private ExecutorService priorityJobScheduler = Executors.newSingleThreadExecutor();

    private PriorityBlockingQueue<Job> priorityQueue;

    @Override
    public Job runJobNow(Job job) {
        jobScheduler.schedule(job, new Date());
        job.setState(State.DONE);
        jobManager.save(job);
        return job;
    }

    @Override
    public List<Job> runJobsNow(List<Job> jobs) {
        List<Job> resultJobs = new ArrayList<>();
        for(Job job: jobs) {
            job = runJobNow(job);
            resultJobs.add(job);
        }

        return resultJobs;
    }

    @Override
    public List<Job> getRunningJobs() {
        return jobManager.getRunningJobs();
    }

    @Override
    public List<Job> getFailedJobs() {
        return jobManager.getFailedJobs();
    }

    @Override
    public List<Job> getCompletedJobs() {
        return jobManager.getCompletedJobs();
    }

    @Override
    public List<Job> getQueuedJobs() {
        return jobManager.getQueuedJobs();
    }

    @Override
    @Scheduled(cron = "10 * * * * *")
    public void enqueueJobs() {

        priorityJobPoolExecutor = Executors.newFixedThreadPool(POOL_SIZE);
        priorityQueue = new PriorityBlockingQueue<>(QUEUE_SIZE,
                Comparator.comparing(Job::getPriority));

        populatePriorityQueue();

        priorityJobScheduler.execute(()->{
            while (true) {
                try {
                    Job currentJob = priorityQueue.take();
                    priorityJobPoolExecutor.execute(currentJob);
                    jobManager.save(currentJob);
                } catch (InterruptedException e) {
                    logger.info(e.getMessage());
                    break;
                }
            }
        });
    }

    @Scheduled(cron = "*/10 * * * * *")
    public void concludeRunningJobs() {
        List<Job> jobs = jobManager.getFailedJobs();
        for (Job job: jobs) {
            job.setState(State.DONE);
            jobManager.save(job);
        }

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

    @PostConstruct
    public void initializeScheduler() {
        jobScheduler.initialize();
    }

    @Scheduled(cron = "10 * * * * *")
    public void runCronJobs() {
        List<Job> cronJobs = jobManager.getJobsWithCron();
        for (Job job: cronJobs) {
           if (job.getState() != State.DONE) {
               scheduleByCron(job);
               jobManager.save(job);
           }
        }
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
        List<Job> jobs = jobManager.getByState(State.QUEUED);
        for (Job job: jobs) {
            enqueueJob(job);
        }
        logger.info("Jobs with State as Queued got queued!" + jobs.size() + " Jobs queued");
    }

}
