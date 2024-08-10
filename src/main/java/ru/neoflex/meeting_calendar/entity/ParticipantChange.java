package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import lombok.Data;

@Entity
@Data
@Table(name = "participant_changes")
public class ParticipantChange {

    @Id
    @Column(name = "meeting_id")
    private Integer meeting_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "old_status_id", nullable = false)
    private MeetingParticipantStatus oldStatus;

    @Column(name = "change_time", nullable = false)
    private Timestamp changeTime;

    @Column(name = "operation", nullable = false, length = 20)
    private String operation;


    // Геттеры и сеттеры
}

// Модель изменения статуса участника