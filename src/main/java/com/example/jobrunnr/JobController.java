package com.example.jobrunnr;

import lombok.extern.slf4j.Slf4j;
import org.jobrunr.jobs.JobId;
import org.jobrunr.jobs.context.JobContext;
import org.jobrunr.scheduling.JobScheduler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
public class JobController {

    private final JobScheduler jobScheduler;
    private final SampleJob sampleJob;

    public JobController(JobScheduler jobScheduler, SampleJob sampleJob) {
        this.jobScheduler = jobScheduler;
        this.sampleJob = sampleJob;
    }

    public record Details(String message) {}

    @PostMapping("/jobs/trigger")
    public Map<String, Object> triggerSampleJob2(@RequestBody Details details) {
        JobId jobId = jobScheduler.enqueue(() -> sampleJob.executeSampleJob2(JobContext.Null, details.message));
        log.info("Created job: {}", jobId);
        return Map.of("jobId", jobId.toString());
    }
}
