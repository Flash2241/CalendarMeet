package ru.neoflex.meeting_calendar.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.meeting_calendar.entity.Reminder;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.ReminderRepository;

import java.sql.Timestamp;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ReminderServiceTest {

    @InjectMocks
    private ReminderService reminderService;

    @Mock
    private ReminderRepository reminderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateReminderThrowsMeetingConflictExceptionWhenReminderExists() {
        Reminder existingReminder = new Reminder();
        existingReminder.setReminderTime(Timestamp.valueOf("2023-01-01 10:00:00"));

        when(reminderRepository.findByReminderTimeBetween(existingReminder.getReminderTime(), existingReminder.getReminderTime()))
                .thenReturn(Collections.singletonList(existingReminder));

        Reminder newReminder = new Reminder();
        newReminder.setReminderTime(Timestamp.valueOf("2023-01-01 10:00:00"));

        assertThrows(MeetingConflictException.class, () -> reminderService.createReminder(newReminder));
    }
}
