package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobId;

    @Column(nullable = false, length = 100)
    private String title;

    @Column
    private String description;

    public String getTitle() {
        return null;
    }

    public void setTitle(String existingJob) {

    }

    // Геттеры и сеттеры
}

// Модель вакансии