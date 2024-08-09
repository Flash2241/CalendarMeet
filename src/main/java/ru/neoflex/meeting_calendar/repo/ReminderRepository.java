package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Reminder;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
}
