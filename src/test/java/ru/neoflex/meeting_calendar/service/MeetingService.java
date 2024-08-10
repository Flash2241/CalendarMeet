package ru.neoflex.meeting_calendar.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantStatus;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.MeetingRepository;
import ru.neoflex.meeting_calendar.repo.MeetingParticipantRepository;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class MeetingServiceTest {

    @InjectMocks
    private MeetingService meetingService;

    @Mock
    private MeetingRepository meetingRepository;

    @Mock
    private MeetingParticipantRepository meetingParticipantRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateMeetingThrowsMeetingConflictExceptionWhenTimeConflictExists() {
        Meeting existingMeeting = new Meeting();
        existingMeeting.setStartTime(Timestamp.valueOf("2023-01-01 10:00:00"));
        existingMeeting.setEndTime(Timestamp.valueOf("2023-01-01 11:00:00"));

        when(meetingRepository.findByStartTimeBetween(existingMeeting.getStartTime(), existingMeeting.getEndTime()))
                .thenReturn(Collections.singletonList(existingMeeting));

        Meeting newMeeting = new Meeting();
        newMeeting.setStartTime(Timestamp.valueOf("2023-01-01 10:30:00"));
        newMeeting.setEndTime(Timestamp.valueOf("2023-01-01 11:30:00"));

        assertThrows(MeetingConflictException.class, () -> meetingService.createMeeting(newMeeting));
    }

    @Test
    public void testAddParticipantToMeetingThrowsMeetingConflictExceptionWhenParticipantAlreadyExists() {
        Meeting meeting = new Meeting();
        meeting.setMeetingId(1);
        User user = new User();
        user.setUserId(1);

        when(meetingParticipantRepository.existsByMeetingAndUser(meeting, user)).thenReturn(true);

        MeetingParticipantStatus status = new MeetingParticipantStatus();

        assertThrows(MeetingConflictException.class, () -> meetingService.addParticipantToMeeting(meeting, user, status));
    }
}
