package ru.neoflex.meeting_calendar.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.MeetingParticipant;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantId;
import ru.neoflex.meeting_calendar.interfaces.Meetings;
//import ru.neoflex.meeting_calendar.service.EmailService;
import ru.neoflex.meeting_calendar.service.MeetingService;

import java.util.List;
import java.util.Optional;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingController implements Meetings {

    private final MeetingService meetingService;
    //private final EmailService emailService;

    @GetMapping
    public List<Meeting> getAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Meeting> getMeetingById(@PathVariable Long id) {
        Optional<Meeting> meeting = meetingService.getMeetingById(id);
        return meeting.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Meeting> createMeeting(Meeting meeting) {
        Meeting createdMeeting = meetingService.createMeeting(meeting);
        //emailService.sendEmailInvitations(createdMeeting);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMeeting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Meeting> updateMeeting(@PathVariable Long id, Meeting meetingDetails) {
        Meeting updatedMeeting = meetingService.updateMeeting(id, meetingDetails);
        if (updatedMeeting != null) {
            return ResponseEntity.ok(updatedMeeting);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMeeting(@PathVariable Long id) {
        meetingService.deleteMeeting(id);
        return ResponseEntity.noContent().build();
    }
}
