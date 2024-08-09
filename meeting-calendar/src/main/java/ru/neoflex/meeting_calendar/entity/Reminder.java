package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "Reminders")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminderId;

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "meetingId")
    private Meeting meeting;

    private ZonedDateTime reminderTime;

    // Getters and setters
    public Long getReminderId() {
        return reminderId;
    }

    public void setReminderId(Long reminderId) {
        this.reminderId = reminderId;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public ZonedDateTime getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(ZonedDateTime reminderTime) {
        this.reminderTime = reminderTime;
    }
}
