package com.truman.jobs.mappers.impl;

import com.truman.jobs.domain.dto.JobListDto;
import com.truman.jobs.domain.entities.Job;
import com.truman.jobs.domain.entities.JobList;
import com.truman.jobs.domain.entities.JobStatus;
import com.truman.jobs.mappers.JobListMapper;
import com.truman.jobs.mappers.JobMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JobListMapperImpl implements JobListMapper {

    private final JobMapper jobMapper;

    public JobListMapperImpl(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public JobList fromDto(JobListDto jobListDto) {
        return new JobList(
                jobListDto.id(),
                jobListDto.title(),
                jobListDto.description(),
                Optional.ofNullable(jobListDto.jobs())
                        .map(jobs -> jobs.stream()
                                .map(jobMapper::fromDto)
                                .toList()
                        ).orElse(null),
                null,
                null
        );
    }

    @Override
    public JobListDto toDto(JobList jobList) {
        return new JobListDto(
                jobList.getId(),
                jobList.getTitle(),
                jobList.getDescription(),
                Optional.ofNullable(jobList.getJobs())
                        .map(List::size)
                        .orElse(0),
                calculateJobListProgress(jobList.getJobs()),
                Optional.ofNullable(jobList.getJobs())
                        .map(jobs ->
                                jobs.stream().map(jobMapper::toDto).toList()
                        ).orElse(null)
        );
    }

    private Double calculateJobListProgress(List<Job> jobs) {
        if(null == jobs) {
            return null;
        }

        long closedJobCount = jobs.stream().filter(job ->
                JobStatus.CLOSED == job.getStatus()
        ).count();

        return (double) closedJobCount / jobs.size();
    }

}
