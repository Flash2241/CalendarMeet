package ru.neoflex.meeting_calendar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.Reminder;
import ru.neoflex.meeting_calendar.service.ReminderService;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {

    private final ReminderService reminderService;

    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public ResponseEntity<String> createReminder(@RequestBody Reminder reminder) {
        reminderService.createReminder(reminder);
        return ResponseEntity.status(HttpStatus.CREATED).body("Reminder created successfully");
    }

    @GetMapping("/{meetingId}")
    public ResponseEntity<List<Reminder>> getRemindersForMeeting(@PathVariable Integer meetingId) {
        List<Reminder> reminders = reminderService.findRemindersByMeeting(new Meeting(meetingId));
        return ResponseEntity.ok(reminders);
    }

    // Другие методы для работы с напоминаниями
}
