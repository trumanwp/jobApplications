package com.truman.jobs.controllers;

import com.truman.jobs.domain.dto.JobDto;
import com.truman.jobs.domain.entities.Job;
import com.truman.jobs.mappers.JobMapper;
import com.truman.jobs.services.JobService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/job-lists/{job_list_id}/jobs")
public class JobsController {

    private final JobService jobService;
    private final JobMapper jobMapper;

    public JobsController(JobService jobService, JobMapper jobMapper) {
        this.jobService = jobService;
        this.jobMapper = jobMapper;
    }

    @GetMapping
    public List<JobDto> listJobs(@PathVariable("job_list_id") UUID jobListId) {
        return jobService.listJobs(jobListId)
                .stream()
                .map(jobMapper::toDto)
                .toList();
    }

    @PostMapping
    public JobDto createJob(
            @PathVariable("job_list_id") UUID jobListId,
            @RequestBody JobDto jobDto){
        Job createdJob = jobService.createJob(
                jobListId,
                jobMapper.fromDto(jobDto)
        );
        return jobMapper.toDto(createdJob);
    }

    @GetMapping(path = "/{job_id}")
    public Optional<JobDto> getjob(
            @PathVariable("job_list_id") UUID jobListId,
            @PathVariable("job_id") UUID jobId
    ) {
        return jobService.getJob(jobListId, jobId).map(jobMapper::toDto);
    }

    @PutMapping(path = "/{job_id}")
    public JobDto updateJob(
            @PathVariable("job_list_id") UUID jobListId,
            @PathVariable("job_id") UUID jobId,
            @RequestBody JobDto jobDto
    ){
        Job updatedJob = jobService.updateJob(
                jobListId,
                jobId,
                jobMapper.fromDto(jobDto)
        );
        return jobMapper.toDto(updatedJob);
    }

    @DeleteMapping(path = "/{job_id}")
    public void deleteJob(
            @PathVariable("job_list_id") UUID jobListId,
            @PathVariable("job_id") UUID jobId
    ){
        jobService.deleteJob(jobListId, jobId);
    }
}
