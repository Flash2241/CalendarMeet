package ru.neoflex.meeting_calendar.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.MeetingParticipant;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantId;

@Repository
public interface ParticipantRepository extends JpaRepository<MeetingParticipant, MeetingParticipantId> {
  List<MeetingParticipant> findByParticipantData_Meeting_MeetingId(Long meetingId);
}
