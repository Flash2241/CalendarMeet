package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "meeting_participants")
@IdClass(MeetingParticipantId.class)
public class MeetingParticipant {

    @Id
    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private MeetingParticipantStatus status;

    // Геттеры и сеттеры
}

// Модель статуса участника