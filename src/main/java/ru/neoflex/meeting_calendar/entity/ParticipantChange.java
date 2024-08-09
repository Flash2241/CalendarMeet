package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "ParticipantsChanges")
public class ParticipantChange {
    @Id
    @Column(name = "participant_change_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long participantChangeId;

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "meeting_id")
    private Meeting meeting;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "old_status_id", referencedColumnName = "meeting_status_id")
    private MeetingParticipantStatus oldStatus;

    private ZonedDateTime changeTime;
    private String operation;

}

// Модель изменения статуса участника