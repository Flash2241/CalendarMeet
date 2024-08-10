package ru.neoflex.meeting_calendar.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.*;
import ru.neoflex.meeting_calendar.repo.MeetingChangeRepository;
import ru.neoflex.meeting_calendar.repo.MeetingParticipantRepository;
import ru.neoflex.meeting_calendar.repo.MeetingRepository;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import java.security.Timestamp;
import java.util.List;
import java.util.Optional;


@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingParticipantRepository meetingParticipantRepository;
    private final MeetingChangeRepository meetingChangeRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository,
                          MeetingParticipantRepository meetingParticipantRepository,
                          MeetingChangeRepository meetingChangeRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingParticipantRepository = meetingParticipantRepository;
        this.meetingChangeRepository = meetingChangeRepository;
    }

    public Meeting createMeeting(Meeting meeting) {
        // Проверка на пересечение времени встреч
        List<Meeting> conflictingMeetings = meetingRepository.findByStartTimeBetween(meeting.getStartTime(), meeting.getEndTime());
        if (!conflictingMeetings.isEmpty()) {
            throw new MeetingConflictException("A meeting already exists during this time period");
        }

        return meetingRepository.save(meeting);
    }

    public void addParticipantToMeeting(Meeting meeting, User user, MeetingParticipantStatus status) {
        // Проверка на существование участника
        if (meetingParticipantRepository.existsByMeetingAndUser(meeting, user)) {
            throw new MeetingConflictException("User is already a participant in this meeting");
        }

        MeetingParticipant participant = new MeetingParticipant();
        participant.setMeeting(meeting);
        participant.setUser(user);
        participant.setStatus(status);
        meetingParticipantRepository.save(participant);
    }

    public List<Meeting> findMeetingsByCreator(User creator) {
        return meetingRepository.findByCreator(creator);
    }

    public List<Meeting> findMeetingsByJob(Job job) {
        return meetingRepository.findByJob(job);
    }

    public void logMeetingChange(Meeting meeting, String oldTitle, Timestamp oldStartTime, Timestamp oldEndTime, Job oldJob, String oldMeetingResult, User changedBy, String operation) {
        MeetingChange change = new MeetingChange();
        change.setMeeting(meeting);
        change.setOldTitle(oldTitle);
        change.setOldStartTime(oldStartTime);
        change.setOldEndTime(oldEndTime);
        change.setOldJob(oldJob);
        change.setOldMeetingResult(oldMeetingResult);
        change.setChangedBy(changedBy);
        change.setChangeTime(new Timestamp(System.currentTimeMillis()));
        change.setOperation(operation);
        meetingChangeRepository.save(change);
    }
}
