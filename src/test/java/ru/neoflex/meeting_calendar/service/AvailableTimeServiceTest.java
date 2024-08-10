package ru.neoflex.meeting_calendar.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.meeting_calendar.entity.AvailableTime;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.AvailableTimeRepository;

import java.sql.Timestamp;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AvailableTimeServiceTest {

    @InjectMocks
    private AvailableTimeService availableTimeService;

    @Mock
    private AvailableTimeRepository availableTimeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddAvailableTimeThrowsMeetingConflictExceptionWhenTimeConflictExists() {
        AvailableTime existingTime = new AvailableTime();
        existingTime.setStartTime(Timestamp.valueOf("2023-01-01 10:00:00"));
        existingTime.setEndTime(Timestamp.valueOf("2023-01-01 11:00:00"));

        when(availableTimeRepository.findByStartTimeBetween(existingTime.getStartTime(), existingTime.getEndTime()))
                .thenReturn(Collections.singletonList(existingTime));

        AvailableTime newTime = new AvailableTime();
        newTime.setStartTime(Timestamp.valueOf("2023-01-01 10:30:00"));
        newTime.setEndTime(Timestamp.valueOf("2023-01-01 11:30:00"));

        assertThrows(MeetingConflictException.class, () -> availableTimeService.addAvailableTime(newTime));
    }
}
