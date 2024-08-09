package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Jobs")
public class Job {
    @Id
    @Column(name = "job_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long jobId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    // Геттеры и сеттеры
}
// Модель вакансии