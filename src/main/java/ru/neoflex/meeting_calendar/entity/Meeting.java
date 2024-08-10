package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.security.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "meetings")
public class Meeting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meetingId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(name = "start_time", nullable = false)
    private Timestamp startTime;

    @Column(name = "end_time", nullable = false)
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;

    @Column(length = 255)
    private String comment;

    public Meeting(Integer meetingId) {

    }

    public Timestamp getStartTime() {
    }

    public Timestamp getEndTime() {
        return null;
    }

    public void setStartTime(java.sql.Timestamp timestamp) {
    }

    public void setEndTime(java.sql.Timestamp timestamp) {
    }

    public void setMeetingId(int i) {

    }

    // Геттеры и сеттеры
}

// Модель встречи