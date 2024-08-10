package ru.neoflex.meeting_calendar.controller;


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

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    private final MeetingService meetingService;
    private final MeetingParticipantStatusService statusService;

    @Autowired
    public MeetingController(MeetingService meetingService, MeetingParticipantStatusService statusService) {
        this.meetingService = meetingService;
        this.statusService = statusService;
    }

    @PostMapping
    public ResponseEntity<String> createMeeting(@RequestBody Meeting meeting) {
        meetingService.createMeeting(meeting);
        return ResponseEntity.status(HttpStatus.CREATED).body("Meeting created successfully");
    }

    @PostMapping("/{meetingId}/participants")
    public ResponseEntity<String> addParticipant(@PathVariable Integer meetingId, @RequestBody Map<String, String> participantData) {
        Optional<Meeting> meetingOpt = meetingService.findMeetingById(meetingId);
        if (!meetingOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Meeting not found");
        }

        Meeting meeting = meetingOpt.get();
        UserService userService = null;
        Optional<User> userOpt = userService.findUserByUsername(participantData.get("username"));
        if (!userOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOpt.get();
        MeetingParticipantStatus status = statusService.findStatusByName(participantData.get("status"));
        meetingService.addParticipantToMeeting(meeting, user, status);

        return ResponseEntity.ok("Participant added successfully");
    }

    // Другие методы для поиска встреч, обновления данных о встречах, изменения участников и т.д.
}
