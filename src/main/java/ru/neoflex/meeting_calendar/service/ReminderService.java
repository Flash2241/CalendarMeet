package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.Reminder;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.ReminderRepository;

import java.security.Timestamp;
import java.util.List;

@Service
public class ReminderService {

    private final ReminderRepository reminderRepository;

    @Autowired
    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public Reminder createReminder(Reminder reminder) {
        // Проверка на существование напоминания на это время
        List<Reminder> existingReminders = reminderRepository.findByReminderTimeBetween(reminder.getReminderTime(), reminder.getReminderTime());
        if (!existingReminders.isEmpty()) {
            throw new MeetingConflictException("Reminder for this time already exists");
        }

        return reminderRepository.save(reminder);
    }

    public List<Reminder> findRemindersByMeeting(Meeting meeting) {
        return reminderRepository.findByMeeting(meeting);
    }

    public List<Reminder> findRemindersWithinPeriod(Timestamp startTime, Timestamp endTime) {
        return reminderRepository.findByReminderTimeBetween(startTime, endTime);
    }
}
