package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.MeetingParticipantStatus;

import java.util.Optional;

@Repository
public interface MeetingParticipantStatusRepository extends JpaRepository<MeetingParticipantStatus, Integer> {

    Optional<MeetingParticipantStatus> findByStatusName(String statusName);
}
// метод поиска статуса по имени