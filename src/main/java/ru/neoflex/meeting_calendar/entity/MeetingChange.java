package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.time.ZonedDateTime;
import lombok.Data;

@Entity
@Data
@Table(name = "meeting_changes")
public class MeetingChange {

    @Id
    @Column(name = "meeting_id", nullable = false)
    private Integer meetingId;

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

    // Геттеры и сеттеры
}


// Изменения встреч