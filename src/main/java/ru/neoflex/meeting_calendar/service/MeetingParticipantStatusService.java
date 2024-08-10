package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantStatus;
import ru.neoflex.meeting_calendar.exceptions.RoleNotFoundException;
import ru.neoflex.meeting_calendar.repo.MeetingParticipantStatusRepository;

@Service
public class MeetingParticipantStatusService {

    private final MeetingParticipantStatusRepository meetingParticipantStatusRepository;

    @Autowired
    public MeetingParticipantStatusService(MeetingParticipantStatusRepository meetingParticipantStatusRepository) {
        this.meetingParticipantStatusRepository = meetingParticipantStatusRepository;
    }

    public MeetingParticipantStatus findStatusByName(String statusName) {
        return meetingParticipantStatusRepository.findByStatusName(statusName)
                .orElseThrow(() -> new RoleNotFoundException("Status not found"));
    }
}
