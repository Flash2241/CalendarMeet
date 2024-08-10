package ru.neoflex.meeting_calendar.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.neoflex.meeting_calendar.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUserEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUserPhoneNumber(String phoneNumber);
}
