package ru.neoflex.meeting_calendar.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.repo.MeetingRepository;

import java.util.List;
import java.util.Optional;


@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public List<Meeting> getAllMeetings() {
        return meetingRepository.findAll();
    }

    public Optional<Meeting> getMeetingById(Long id) {
        return meetingRepository.findById(id);
    }

    public Meeting createMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public Meeting updateMeeting(Long id, Meeting meetingDetails) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(id);

        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            meeting.setTitle(meetingDetails.getTitle());
            meeting.setStartTime(meetingDetails.getStartTime());
            meeting.setEndTime(meetingDetails.getEndTime());
            meeting.setMeetingResult(meetingDetails.getMeetingResult());
            return meetingRepository.save(meeting);
        }

        return null;  // лучше бросать исключение, если встреча не найдена
    }

    public void deleteMeeting(Long id) {
        meetingRepository.deleteById(id);
    }
}
