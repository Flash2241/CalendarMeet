package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MeetingParticipants")
public class MeetingParticipant {
    @EmbeddedId
    private MeetingParticipantId id;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private MeetingParticipantStatus status;

    // Геттеры и сеттеры
}
// Модель статуса участника