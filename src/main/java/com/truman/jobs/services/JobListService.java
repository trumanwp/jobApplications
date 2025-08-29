package com.truman.jobs.services;

import com.truman.jobs.domain.entities.JobList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JobListService {
    List<JobList> listJobLists();
    JobList createJobList(JobList jobList);
    Optional<JobList> getJobList(UUID id);
    JobList updateJobList(UUID jobListId, JobList jobList);
    void deleteJobList(UUID jobListId);
}
