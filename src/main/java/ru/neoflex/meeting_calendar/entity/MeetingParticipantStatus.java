package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "MeetingParticipantsStatus")
public class MeetingParticipantStatus {
    @Id
    @Column(name = "meeting_status_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long meetingStatusId;

    @Column(name = "status_name", nullable = false)
    private String name;

    @Column(name = "status_description")
    private String description;

    // Геттеры и сеттеры
}
