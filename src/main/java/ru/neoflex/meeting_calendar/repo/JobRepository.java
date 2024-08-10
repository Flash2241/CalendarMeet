package ru.neoflex.meeting_calendar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.Job;

import java.util.Optional;

@Repository
public interface JobRepository extends JpaRepository<Job, Integer> {
// Метод поиска вакансий по названию
    Optional<Job> findByTitle(String title);
}
