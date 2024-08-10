package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.ParticipantChange;
import ru.neoflex.meeting_calendar.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParticipantChangeRepository extends JpaRepository<ParticipantChange, Integer> {

    List<ParticipantChange> findByMeeting(Meeting meeting);

    List<ParticipantChange> findByUser(User user);

    Optional<Object> findByMeetingAndUser(Meeting meeting, User user);
}
