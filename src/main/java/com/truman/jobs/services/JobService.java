package com.truman.jobs.services;

import com.truman.jobs.domain.entities.Job;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobService {
    List<Job> listJobs(UUID jobListId);
    Job createJob(UUID jobListId, Job job);
    Optional<Job> getJob(UUID jobListId, UUID jobId);
    Job updateJob(UUID jobListId, UUID jobId, Job job);
    void deleteJob(UUID jobListId, UUID jobId);
}
