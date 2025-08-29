package com.truman.jobs.mappers.impl;

import com.truman.jobs.domain.dto.JobDto;
import com.truman.jobs.domain.entities.Job;
import com.truman.jobs.mappers.JobMapper;
import org.springframework.stereotype.Component;

@Component
public class JobMapperImpl implements JobMapper {

    @Override
    public Job fromDto(JobDto jobDto) {
        return new Job(
                jobDto.id(),
                jobDto.title(),
                jobDto.description(),
                jobDto.dueDate(),
                jobDto.status(),
                jobDto.priority(),
                null,
                null,
                null
        );
    }

    @Override
    public JobDto toDto(Job job) {
        return new JobDto(
                job.getId(),
                job.getTitle(),
                job.getDescription(),
                job.getDueDate(),
                job.getPriority(),
                job.getStatus()
        );
    }

}
