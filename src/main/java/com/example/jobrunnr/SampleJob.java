package com.example.jobrunnr;

import org.jobrunr.jobs.annotations.Job;
import org.jobrunr.jobs.annotations.Recurring;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.jobs.context.JobDashboardProgressBar;
import org.jobrunr.jobs.context.JobRunrDashboardLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class SampleJob {

    Logger log = new JobRunrDashboardLogger(LoggerFactory.getLogger(SampleJob.class));

    @Job(name = "Sample job 2", retries = 1)
    public void executeSampleJob2(JobContext jobContext, String message) throws InterruptedException {
        log.info("Sample job 2 starting...");
        Thread.sleep(2000);
        log.info("Message received: {}", message);
        log.info("Sample job 2 completed");
    }

    @Recurring(id = "my-recurring-job", cron = "* * * * *")
    @Job(name = "My recurring job", labels = {"recurring", "job"}, retries = 1)
    public void executeSampleJob(JobContext jobContext) throws InterruptedException {
        int total = 60;
        JobDashboardProgressBar progressBar = jobContext.progressBar(total);
        log.info("Sample job starting...");
        for (int i = 0; i < total; i++) {
            Thread.sleep(500);
            log.info("Progress: {}/{}", i+1, total);
            progressBar.setProgress(i+1);
        }
        log.info("Sample job completed");
    }
}
