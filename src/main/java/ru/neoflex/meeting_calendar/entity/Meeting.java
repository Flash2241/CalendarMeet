package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.batch.BatchProperties;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Meetings")
public class Meeting {
    @Id
    @Column(name = "meeting_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingId;

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
    private Job job;

    private String comment;

    // Геттеры и сеттеры
}
// Модель встречи