package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.MeetingParticipant;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantId;
import ru.neoflex.meeting_calendar.entity.User;

import java.util.List;

@Repository
public interface MeetingParticipantRepository extends JpaRepository<MeetingParticipant, MeetingParticipantId> {

    List<MeetingParticipant> findByMeeting(Meeting meeting);

    List<MeetingParticipant> findByUser(User user);

    boolean existsByMeetingAndUser(Meeting meeting, User user);
}
