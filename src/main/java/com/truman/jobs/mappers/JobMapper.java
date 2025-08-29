package com.truman.jobs.mappers;

import com.truman.jobs.domain.dto.JobDto;
import com.truman.jobs.domain.entities.Job;

public interface JobMapper {

    Job fromDto(JobDto jobDto);

    JobDto toDto(Job job);

}
