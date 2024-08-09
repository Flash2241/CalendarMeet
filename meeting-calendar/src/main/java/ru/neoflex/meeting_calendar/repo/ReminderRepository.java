package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.meeting_calendar.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
