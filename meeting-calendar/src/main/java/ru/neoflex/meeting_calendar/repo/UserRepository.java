package ru.neoflex.meeting_calendar.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.neoflex.meeting_calendar.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
