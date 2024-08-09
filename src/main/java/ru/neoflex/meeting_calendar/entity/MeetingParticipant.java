package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
@Table(name = "MeetingParticipants")
public class MeetingParticipant {
    @EmbeddedId
    @Column(name = "meeting_participant_id")
    private MeetingParticipantId participantData;

    
    @ManyToOne
    @JoinColumn(name = "status_id")
    private MeetingParticipantStatus status;

    // Геттеры и сеттеры
}
// Модель статуса участника