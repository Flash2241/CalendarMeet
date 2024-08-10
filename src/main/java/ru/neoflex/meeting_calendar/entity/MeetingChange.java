package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.security.Timestamp;
import java.time.ZonedDateTime;

@Entity
@Table(name = "meeting_changes")
public class MeetingChange {

    @Id
    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private Meeting meeting;

    @Column(name = "old_title", nullable = false, length = 100)
    private String oldTitle;

    @Column(name = "old_start_time", nullable = false)
    private Timestamp oldStartTime;

    @Column(name = "old_end_time", nullable = false)
    private Timestamp oldEndTime;

    @ManyToOne
    @JoinColumn(name = "old_job_id", nullable = false)
    private Job oldJob;

    @Column(name = "old_meeting_result")
    private String oldMeetingResult;

    @ManyToOne
    @JoinColumn(name = "changed_by", nullable = false)
    private User changedBy;

    @Column(name = "change_time", nullable = false)
    private Timestamp changeTime;

    @Column(name = "operation", nullable = false, length = 20)
    private String operation;

    public void setMeeting(Meeting meeting) {
    }

    public void setOldTitle(String oldTitle) {
    }

    public void setOldStartTime(Timestamp oldStartTime) {
    }

    public void setOldEndTime(Timestamp oldEndTime) {
    }

    public void setOldJob(Job oldJob) {
    }

    public void setOldMeetingResult(String oldMeetingResult) {
    }

    public void setChangedBy(User changedBy) {
    }

    public void setChangeTime(Timestamp timestamp) {
    }

    public void setOperation(String operation) {

    }

    // Геттеры и сеттеры
}


// Изменения встреч