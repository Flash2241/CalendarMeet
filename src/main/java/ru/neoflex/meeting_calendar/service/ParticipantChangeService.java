package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantStatus;
import ru.neoflex.meeting_calendar.entity.ParticipantChange;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.ParticipantChangeRepository;

import java.security.Timestamp;

@Service
public class ParticipantChangeService {

    private final ParticipantChangeRepository participantChangeRepository;

    @Autowired
    public ParticipantChangeService(ParticipantChangeRepository participantChangeRepository) {
        this.participantChangeRepository = participantChangeRepository;
    }

    public void logParticipantChange(Meeting meeting, User user, MeetingParticipantStatus oldStatus, String operation) {
        if (participantChangeRepository.findByMeetingAndUser(meeting, user).isPresent()) {
            throw new MeetingConflictException("Participant change for this meeting already logged");
        }

        ParticipantChange change = new ParticipantChange();
        change.setMeeting(meeting);
        change.setUser(user);
        change.setOldStatus(oldStatus);
        change.setChangeTime(new Timestamp(System.currentTimeMillis()));
        change.setOperation(operation);
        participantChangeRepository.save(change);
    }
}
