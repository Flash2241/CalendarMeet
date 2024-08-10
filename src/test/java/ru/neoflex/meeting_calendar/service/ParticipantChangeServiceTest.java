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
import ru.neoflex.meeting_calendar.repo.ParticipantChangeRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class ParticipantChangeServiceTest {

    @InjectMocks
    private ParticipantChangeService participantChangeService;

    @Mock
    private ParticipantChangeRepository participantChangeRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testLogParticipantChangeThrowsMeetingConflictExceptionWhenChangeAlreadyExists() {
        Meeting meeting = new Meeting();
        User user = new User();

        when(participantChangeRepository.findByMeetingAndUser(meeting, user)).thenReturn(Optional.of(new MeetingParticipantStatus()));

        assertThrows(MeetingConflictException.class, () -> participantChangeService.logParticipantChange(meeting, user, new MeetingParticipantStatus(), "UPDATE"));
    }
}
