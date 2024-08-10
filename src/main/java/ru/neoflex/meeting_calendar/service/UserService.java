package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Role;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.exceptions.RoleNotFoundException;
import ru.neoflex.meeting_calendar.repo.RoleRepository;
import ru.neoflex.meeting_calendar.repo.UserRepository;
import ru.neoflex.meeting_calendar.exceptions.UserNotFoundException;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;

import java.util.Optional;

@Service
public class UserService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(User user) {
        if (userRepository.findByUserEmail(user.getUserEmail()).isPresent()) {
            throw new MeetingConflictException("User with this email already exists");
        }

        Role userRole = roleRepository.findByRoleName(user.getUserRole().getRoleName())
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
        user.setUserRole(userRole);

        // Шифруем пароль перед сохранением
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public User findUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    // Метод для загрузки пользователя по имени пользователя
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        return org.springframework.security.core.userdetails.User.withUsername(user.getUserEmail())
                .password(user.getPassword())
                .authorities(user.getUserRole().getRoleName())
                .build();
    }

    // Метод для проверки учетных данных пользователя
    public boolean validateUserCredentials(String email, String rawPassword) {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
        return passwordEncoder.matches(rawPassword, user.getPassword());
    }
}
