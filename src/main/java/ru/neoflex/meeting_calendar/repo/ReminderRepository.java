package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.Reminder;

import java.security.Timestamp;
import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Integer> {

    List<Reminder> findByMeeting(Meeting meeting);

    List<Reminder> findByReminderTimeBetween(Timestamp startTime, Timestamp endTime);
}
