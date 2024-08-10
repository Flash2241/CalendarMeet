package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "meeting_participant_statuses")
public class MeetingParticipantStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer meetingStatusId;

    @Column(name = "status_name", nullable = false, length = 30)
    private String statusName;

    @Column(name = "status_description")
    private String statusDescription;

    // Геттеры и сеттеры
}

