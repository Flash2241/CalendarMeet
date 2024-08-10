package ru.neoflex.meeting_calendar.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.meeting_calendar.entity.*;
import ru.neoflex.meeting_calendar.interfaces.Meetings;
//import ru.neoflex.meeting_calendar.service.EmailService;
import ru.neoflex.meeting_calendar.service.MeetingParticipantStatusService;
import ru.neoflex.meeting_calendar.service.MeetingService;
import ru.neoflex.meeting_calendar.service.UserService;

import java.util.Map;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;

    private final UserService userService;

    private final MeetingParticipantStatusService statusService;


    @PostMapping
    public ResponseEntity<String> createMeeting(@RequestBody Meeting meeting) {
        meetingService.createMeeting(meeting);
        return ResponseEntity.status(HttpStatus.CREATED).body("Meeting created successfully");
    }

    @PostMapping("/{meetingId}/participants")
    public ResponseEntity<String> addParticipant(@PathVariable Integer meetingId, @RequestBody Map<String, String> participantData) {
        Optional<Meeting> meetingOpt = meetingService.findMeetingById(meetingId);
        if (meetingOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meeting not found");
        }

        Meeting meeting = meetingOpt.get();
        Optional<User> userOpt = userService.findUserByUsername(participantData.get("username"));
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOpt.get();
        MeetingParticipantStatus status = statusService.findStatusByName(participantData.get("status"));
        meetingService.addParticipantToMeeting(meeting, user, status);

        return ResponseEntity.ok("Participant added successfully");
    }

    // Другие методы для поиска встреч, обновления данных о встречах, изменения участников и т.д.

    public List<Meeting> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    public ResponseEntity<Optional<Meeting>> getMeetingById(Integer id) {
        return meetingService.getMeetingById(id);
    }

    public Meeting updateMeeting(Integer id, Meeting meetingDetails) {
        Optional<Meeting> meetingOptional = meetingService.findMeetingById(id);

        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            meeting.setTitle(meetingDetails.getTitle());
            meeting.setStartTime(meetingDetails.getStartTime());
            meeting.setEndTime(meetingDetails.getEndTime());
            meeting.setComment(meetingDetails.getComment());
            return meetingService.createMeeting(meeting);
        }

        return null;  // лучше бросать исключение, если встреча не найдена
    }

    public void deleteMeeting(Integer id) {
        meetingService.deleteMeeting(id);
    }
}
