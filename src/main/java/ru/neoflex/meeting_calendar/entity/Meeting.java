package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.time.LocalDateTime;

@Entity
@Table(name = "Meetings")
public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private BatchProperties.Job job;

    @Column(name = "meeting_result")
    private String meetingResult;

    // Геттеры и сеттеры
}
// Модель встречи