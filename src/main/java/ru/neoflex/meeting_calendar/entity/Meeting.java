package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "meetings")
public class Meeting {

    @Id
    @Column(name = "meeting_id")
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

    // Геттеры и сеттеры
}

// Модель встречи