package com.truman.jobs.services.impl;

import com.truman.jobs.repositories.JobListRepository;
import com.truman.jobs.repositories.JobRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DatabaseResetService {

    private final JobRepository jobRepository;
    private final JobListRepository jobListRepository;

    public DatabaseResetService(JobRepository jobRepository, JobListRepository jobListRepository) {
        this.jobRepository = jobRepository;
        this.jobListRepository = jobListRepository;
    }

    // Resets the database every 24 hours
    @Scheduled(cron = "0 0 0 * * ?")  // midnight every day
    public void resetDatabase() {
        jobRepository.deleteAll();
        jobListRepository.deleteAll();
        System.out.println("Database has been automatically reset at midnight.");
    }
}
