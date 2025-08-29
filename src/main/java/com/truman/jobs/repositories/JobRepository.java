package com.truman.jobs.repositories;

import com.truman.jobs.domain.entities.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JobRepository extends JpaRepository<Job, UUID> {
    List<Job> findByJobListId(UUID jobListId);
    Optional<Job> findByJobListIdAndId(UUID jobListId, UUID id);
    void deleteByJobListIdAndId(UUID jobListId, UUID id);
}
