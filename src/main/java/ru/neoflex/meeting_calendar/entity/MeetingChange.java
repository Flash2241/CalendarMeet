package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "MeetingChanges")
public class MeetingChange {
    @Id
    @Column(name = "change_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeId;

    private String oldTitle;
    private ZonedDateTime oldStartTime;
    private ZonedDateTime oldEndTime;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "user_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "old_job_id", referencedColumnName = "job_id")
    private Job oldJob;

    private String oldMeetingResult;
    private ZonedDateTime changeTime;
    private String operation;

    // Getters and setters
}
// Изменения встреч