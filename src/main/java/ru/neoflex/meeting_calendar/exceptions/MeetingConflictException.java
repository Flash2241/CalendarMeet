package ru.neoflex.meeting_calendar.exceptions;

public class MeetingConflictException extends RuntimeException {
    public MeetingConflictException(String message) {
        super(message);
    }
}