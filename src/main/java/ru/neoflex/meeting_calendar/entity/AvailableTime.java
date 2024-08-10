package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "available_times")
public class AvailableTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer availabilityId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    public Timestamp getStartTime() {
        return null;
    }

    public void setStartTime(java.sql.Timestamp timestamp) {
    }

    public void setEndTime(java.sql.Timestamp timestamp) {
    }

    public Timestamp getEndTime() {
        return null;
    }

    // Геттеры и сеттеры
}
