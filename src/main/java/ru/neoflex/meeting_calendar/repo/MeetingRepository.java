package ru.neoflex.meeting_calendar.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Job;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.User;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    List<Meeting> findByCreator(User creator);

    List<Meeting> findByJob(Job job);

    // Дополнительно можно добавить методы поиска по дате и времени
    List<Meeting> findByStartTimeBetween(Timestamp startTime, Timestamp endTime);
}
