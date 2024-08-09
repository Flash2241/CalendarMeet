package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MeetingParticipantsStatus")
public class MeetingParticipantStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status_name", nullable = false)
    private String name;

    @Column(name = "status_description")
    private String description;

    // Геттеры и сеттеры
}
