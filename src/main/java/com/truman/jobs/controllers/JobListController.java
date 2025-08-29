package com.truman.jobs.controllers;

import com.truman.jobs.domain.dto.JobListDto;
import com.truman.jobs.domain.entities.JobList;
import com.truman.jobs.mappers.JobListMapper;
import com.truman.jobs.services.JobListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/job-lists")
public class JobListController {

    private final JobListService jobListService;
    private final JobListMapper jobListMapper;

    public JobListController(JobListService jobListService, JobListMapper jobListMapper) {
        this.jobListService = jobListService;
        this.jobListMapper = jobListMapper;
    }

    @GetMapping
    public List<JobListDto> listJobLists() {
        return jobListService.listJobLists()
                .stream()
                .map(jobListMapper::toDto)
                .toList();
    }

    @PostMapping
    public JobListDto createJobList(@RequestBody JobListDto jobListDto) {
        JobList createdJobList = jobListService.createJobList(
                jobListMapper.fromDto(jobListDto)
        );
        return jobListMapper.toDto(createdJobList);
    }

    @GetMapping(path = "/{job_list_id}")
    public Optional<JobListDto> getJobList(@PathVariable("job_list_id") UUID jobListId){
        return jobListService.getJobList(jobListId).map(jobListMapper::toDto);
    }

    @PutMapping(path = "/{job_list_id}")
    public JobListDto updateJobList(
            @PathVariable("job_list_id") UUID jobListId,
            @RequestBody JobListDto jobListDto
    ) {
        JobList updatedJobList = jobListService.updateJobList(
                jobListId,
                jobListMapper.fromDto(jobListDto)
        );

        return jobListMapper.toDto(updatedJobList);
    }

    @DeleteMapping(path = "/{job_list_id}")
    public void deleteJobList(@PathVariable("job_list_id") UUID jobListId) {
        jobListService.deleteJobList(jobListId);
    }

}
