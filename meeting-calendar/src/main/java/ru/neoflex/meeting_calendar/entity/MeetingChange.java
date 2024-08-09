package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.time.ZonedDateTime;

@Entity
@Table(name = "MeetingChanges")
public class MeetingChange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long changeId;

    @ManyToOne
    @JoinColumn(name = "meeting_id", referencedColumnName = "meetingId")
    private Meeting meeting;

    private String oldTitle;
    private ZonedDateTime oldStartTime;
    private ZonedDateTime oldEndTime;

    @ManyToOne
    @JoinColumn(name = "creator_id", referencedColumnName = "userId")
    private User creator;

    @ManyToOne
    @JoinColumn(name = "old_job_id", referencedColumnName = "jobId")
    private Job oldJob;

    private String oldMeetingResult;
    private ZonedDateTime changeTime;
    private String operation;

    // Getters and setters
}
// Изменения встреч