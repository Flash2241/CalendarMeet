package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "participant_changes")
public class ParticipantChange {

    @Id
    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

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

    public void setOperation(String operation) {
    }

    public void setChangeTime(Timestamp timestamp) {
    }

    public void setOldStatus(MeetingParticipantStatus oldStatus) {
    }

    public void setUser(User user) {
    }

    public void setMeeting(Meeting meeting) {

    }

    // Геттеры и сеттеры
}

// Модель изменения статуса участника