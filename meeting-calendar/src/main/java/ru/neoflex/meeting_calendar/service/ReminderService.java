package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Reminder;
import ru.neoflex.meeting_calendar.repo.ReminderRepository;

import java.util.List;

@Service
public class ReminderService {
    private final ReminderRepository reminderRepository;

    @Autowired
    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }

    public Reminder createReminder(Reminder reminder) {
        return reminderRepository.save(reminder);
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }

    public void deleteReminder(Long id) {
        reminderRepository.deleteById(id);
    }
}
