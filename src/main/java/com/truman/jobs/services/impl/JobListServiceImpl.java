package com.truman.jobs.services.impl;

import com.truman.jobs.domain.entities.JobList;
import com.truman.jobs.repositories.JobListRepository;
import com.truman.jobs.services.JobListService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class JobListServiceImpl implements JobListService {

    private final JobListRepository jobListRepository;

    public JobListServiceImpl(JobListRepository jobListRepository) {
        this.jobListRepository = jobListRepository;
    }

    @Override
    public List<JobList> listJobLists() {
        return jobListRepository.findAll();
    }

    @Override
    public JobList createJobList(JobList jobList) {
        if(null != jobList.getId()) {
            throw new IllegalArgumentException("Job list already has an ID!");
        }
        if(null == jobList.getTitle() || jobList.getTitle().isBlank()) {
            throw new IllegalArgumentException("Job list title must be present!");
        }

        LocalDateTime now = LocalDateTime.now();
        return jobListRepository.save(new JobList(
                null,
                jobList.getTitle(),
                jobList.getDescription(),
                null,
                now,
                now
        ));
    }

    @Override
    public Optional<JobList> getJobList(UUID id) {
        return jobListRepository.findById(id);
    }

    @Transactional
    @Override
    public JobList updateJobList(UUID jobListId, JobList jobList) {
        if(null == jobList.getId()) {
            throw new IllegalArgumentException("Job list must have an ID");
        }

        if(!Objects.equals(jobList.getId(), jobListId)) {
            throw new IllegalArgumentException("Attempting to change job list ID, this is not permitted!");
        }

        JobList existingJobList = jobListRepository.findById(jobListId).orElseThrow(() ->
                new IllegalArgumentException("Job list not found!"));

        existingJobList.setTitle(jobList.getTitle());
        existingJobList.setDescription(jobList.getDescription());
        existingJobList.setUpdated(LocalDateTime.now());
        return jobListRepository.save(existingJobList);
    }

    @Override
    public void deleteJobList(UUID jobListId) {
        jobListRepository.deleteById(jobListId);
    }

}
