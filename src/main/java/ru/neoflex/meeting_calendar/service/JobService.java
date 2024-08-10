package ru.neoflex.meeting_calendar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Job;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.JobRepository;

import java.util.Optional;

@Service
public class JobService {

    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job) {
        if (jobRepository.findByTitle(job.getTitle()).isPresent()) {
            throw new MeetingConflictException("Job with this title already exists");
        }
        return jobRepository.save(job);
    }

    public Optional<Job> findJobByTitle(String title) {
        return jobRepository.findByTitle(title)
                .orElseThrow(() -> new MeetingConflictException("Job with this title not found"));
    }
}
