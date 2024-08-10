package ru.neoflex.meeting_calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.neoflex.meeting_calendar.entity.AvailableTime;
import ru.neoflex.meeting_calendar.service.AvailableTimeService;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
public class AvailableTimeController {

    private final AvailableTimeService availableTimeService;
    private AvailableTime availableTime;

    @Autowired
    public AvailableTimeController(AvailableTimeService availableTimeService) {
        this.availableTimeService = availableTimeService;
    }

    @PostMapping
    public ResponseEntity<String> addAvailableTime(@RequestBody AvailableTime availableTime) {
        this.availableTime = availableTime;
        availableTimeService.addAvailableTime(availableTime);
        return ResponseEntity.status(HttpStatus.CREATED).body("Available time added successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<AvailableTime>> getAvailableTimesByUser(@PathVariable Integer userId) {
        List<AvailableTime> availableTimes = availableTimeService.findAvailableTimesByUser(new User(userId));
        return ResponseEntity.ok(availableTimes);
    }

    // Другие методы для работы с доступным временем
}
