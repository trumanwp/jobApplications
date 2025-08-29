package com.truman.jobs.domain.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "job_lists")
public class JobList {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "jobList", cascade = {
            CascadeType.REMOVE, CascadeType.PERSIST
    })
    private List<Job> jobs;

    @Column(name = "created", nullable = false)
    private LocalDateTime created;

    @Column(name = "updated", nullable = false)
    private LocalDateTime updated;

    public JobList() {
    }

    public JobList(UUID id, String title, String description, List<Job> jobs, LocalDateTime created, LocalDateTime updated) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.jobs = jobs;
        this.created = created;
        this.updated = updated;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDateTime updated) {
        this.updated = updated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JobList jobList = (JobList) o;
        return Objects.equals(id, jobList.id) && Objects.equals(title, jobList.title) && Objects.equals(description, jobList.description) && Objects.equals(jobs, jobList.jobs) && Objects.equals(created, jobList.created) && Objects.equals(updated, jobList.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, description, jobs, created, updated);
    }

    @Override
    public String toString() {
        return "JobList{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", jobs=" + jobs +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
