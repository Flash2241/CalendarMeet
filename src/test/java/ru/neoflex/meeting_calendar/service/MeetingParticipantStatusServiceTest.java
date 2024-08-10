package ru.neoflex.meeting_calendar.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantStatus;
import ru.neoflex.meeting_calendar.exceptions.RoleNotFoundException;
import ru.neoflex.meeting_calendar.repo.MeetingParticipantStatusRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class MeetingParticipantStatusServiceTest {

    @InjectMocks
    private MeetingParticipantStatusService meetingParticipantStatusService;

    @Mock
    private MeetingParticipantStatusRepository meetingParticipantStatusRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindStatusByNameThrowsRoleNotFoundExceptionWhenStatusNotFound() {
        when(meetingParticipantStatusRepository.findByStatusName(anyString())).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> meetingParticipantStatusService.findStatusByName("NON_EXISTING_STATUS"));
    }
}
