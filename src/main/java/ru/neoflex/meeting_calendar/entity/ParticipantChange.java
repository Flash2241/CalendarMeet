package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ParticipantsChanges")
public class ParticipantChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeId;

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "meetingId")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "old_status_id", referencedColumnName = "meetingStatusId")
    private MeetingParticipantStatus oldStatus;

    private ZonedDateTime changeTime;
    private String operation;

    // Getters and setters
}

// Модель изменения статуса участника