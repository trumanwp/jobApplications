package com.truman.jobs.services.impl;

import com.truman.jobs.domain.entities.Job;
import com.truman.jobs.domain.entities.JobList;
import com.truman.jobs.domain.entities.JobPriority;
import com.truman.jobs.domain.entities.JobStatus;
import com.truman.jobs.repositories.JobListRepository;
import com.truman.jobs.repositories.JobRepository;
import com.truman.jobs.services.JobService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final JobListRepository jobListRepository;

    public JobServiceImpl(JobRepository jobRepository, JobListRepository jobListRepository) {
        this.jobRepository = jobRepository;
        this.jobListRepository = jobListRepository;
    }

    @Override
    public List<Job> listJobs(UUID jobListId) {
        return jobRepository.findByJobListId(jobListId);
    }

    @Transactional
    @Override
    public Job createJob(UUID jobListId, Job job) {
        if(null != job.getId()) {
            throw new IllegalArgumentException("Job already has an ID!");
        }
        if(null == job.getTitle() || job.getTitle().isBlank()) {
            throw new IllegalArgumentException("Job must have a title!");
        }

        JobPriority jobPriority = Optional.ofNullable(job.getPriority())
                .orElse(JobPriority.MEDIUM);

        JobStatus jobStatus = JobStatus.OPEN;

        JobList jobList = jobListRepository.findById(jobListId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Job List ID provided!"));

        LocalDateTime now = LocalDateTime.now();
        Job jobToSave = new Job(
                null,
                job.getTitle(),
                job.getDescription(),
                job.getDueDate(),
                jobStatus,
                jobPriority,
                jobList,
                now,
                now
        );

        return jobRepository.save(jobToSave);
    }

    @Override
    public Optional<Job> getJob(UUID jobListId, UUID jobId) {
        return jobRepository.findByJobListIdAndId(jobListId, jobId);
    }

    @Transactional
    @Override
    public Job updateJob(UUID jobListId, UUID jobId, Job job) {
        if (null == job.getId()) {
            throw new IllegalArgumentException("Job must have an ID!");
        }
        if (!Objects.equals(jobId, job.getId())){
            throw new IllegalArgumentException("Job IDs do not match!");
        }
        if (null == job.getPriority()){
            throw new IllegalArgumentException("Job must have a valid priority!");
        }
        if (null == job.getStatus()){
            throw new IllegalArgumentException("Job must have a valid status!");
        }

        Job existingJob = jobRepository.findByJobListIdAndId(jobListId, jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found!"));

        existingJob.setTitle(job.getTitle());
        existingJob.setDescription(job.getDescription());
        existingJob.setDueDate(job.getDueDate());
        existingJob.setPriority(job.getPriority());
        existingJob.setStatus(job.getStatus());
        existingJob.setUpdated(LocalDateTime.now());

        return jobRepository.save(existingJob);

    }

    @Transactional
    @Override
    public void deleteJob(UUID jobListId, UUID jobId) {
        jobRepository.deleteByJobListIdAndId(jobListId, jobId);
    }

}
