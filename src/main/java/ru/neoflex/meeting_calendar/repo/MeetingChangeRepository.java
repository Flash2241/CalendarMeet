package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.MeetingChange;
import ru.neoflex.meeting_calendar.entity.User;

import java.util.List;

@Repository
public interface MeetingChangeRepository extends JpaRepository<MeetingChange, Integer> {

    List<MeetingChange> findByMeeting(Meeting meeting);

    List<MeetingChange> findByChangedBy(User user);
}
// для сущности MeetingChange