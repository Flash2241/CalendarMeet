package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.*;
import ru.neoflex.meeting_calendar.interfaces.Meetings;
import ru.neoflex.meeting_calendar.repo.MeetingParticipantRepository;
import ru.neoflex.meeting_calendar.repo.MeetingRepository;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import java.util.List;
import java.util.Optional;


@Service
public class MeetingService implements Meetings {

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

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public ResponseEntity<Optional<Meeting>> getMeetingById(Integer id) {
        Optional<Meeting> meeting = meetingRepository.findById(id);
        return ResponseEntity.ok(meeting);
    }

    public Meeting updateMeeting(Integer id, Meeting meetingDetails) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(id);

        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            meeting.setTitle(meetingDetails.getTitle());
            meeting.setStartTime(meetingDetails.getStartTime());
            meeting.setEndTime(meetingDetails.getEndTime());
            meeting.setComment(meetingDetails.getComment());
            return meetingRepository.save(meeting);
        }

        return null;  // лучше бросать исключение, если встреча не найдена
    }

    public void deleteMeeting(Integer id) {
        meetingRepository.deleteById(id);
    }

}
