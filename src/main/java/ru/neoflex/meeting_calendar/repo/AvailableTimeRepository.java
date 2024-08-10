package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.AvailableTime;
import ru.neoflex.meeting_calendar.entity.User;

import java.security.Timestamp;
import java.util.List;

@Repository
public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Integer> {

    List<AvailableTime> findByUser(User user);

    List<AvailableTime> findByStartTimeBetween(Timestamp startTime, Timestamp endTime);
}
