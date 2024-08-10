package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "Reminders")
public class Reminder {
    @Id
    @Column(name = "reminder_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reminderId;

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "meeting_id")
    private Meeting meeting;

    private Timestamp reminderTime;

    // Getters and setters
}
