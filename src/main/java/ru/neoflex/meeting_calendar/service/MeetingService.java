package ru.neoflex.meeting_calendar.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.*;
import ru.neoflex.meeting_calendar.repo.MeetingParticipantRepository;
import ru.neoflex.meeting_calendar.repo.MeetingRepository;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import java.util.List;
import java.util.Optional;


@Service
public class MeetingService {

    private final MeetingRepository meetingRepository;
    private final MeetingParticipantRepository meetingParticipantRepository;

    @Autowired
    public MeetingService(MeetingRepository meetingRepository,
                          MeetingParticipantRepository meetingParticipantRepository) {
        this.meetingRepository = meetingRepository;
        this.meetingParticipantRepository = meetingParticipantRepository;
    }

    public Meeting createMeeting(Meeting meeting) {
        // Проверка на пересечение времени встреч
        List<Meeting> conflictingMeetings = meetingRepository.findByStartTimeBetween(meeting.getStartTime(), meeting.getEndTime());
        if (!conflictingMeetings.isEmpty()) {
            throw new MeetingConflictException("A meeting already exists during this time period");
        }

        return meetingRepository.save(meeting);
    }

    public Optional<Meeting> findMeetingById(Integer id) {
        return meetingRepository.findById(id);
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

}
