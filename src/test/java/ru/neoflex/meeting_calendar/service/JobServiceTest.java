package ru.neoflex.meeting_calendar.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.meeting_calendar.entity.Job;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.JobRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class JobServiceTest {

    @InjectMocks
    private JobService jobService;

    @Mock
    private JobRepository jobRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateJobThrowsMeetingConflictExceptionWhenJobExists() {
        Job existingJob = new Job();
        existingJob.setTitle("Existing Job");

        when(jobRepository.findByTitle(anyString())).thenReturn(Optional.of(existingJob));

        Job newJob = new Job();
        newJob.setTitle("Existing Job");

        assertThrows(MeetingConflictException.class, () -> jobService.createJob(newJob));
    }
}
