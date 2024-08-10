package ru.neoflex.meeting_calendar.service;



import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.entity.Role;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.exceptions.UserNotFoundException;
import ru.neoflex.meeting_calendar.exceptions.RoleNotFoundException;
import ru.neoflex.meeting_calendar.repo.UserRepository;
import ru.neoflex.meeting_calendar.repo.RoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleRepository roleRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateUserThrowsMeetingConflictExceptionWhenUserExists() {
        User existingUser = new User();
        existingUser.setUserEmail("test@example.com");

        when(userRepository.findByUserEmail(anyString())).thenReturn(Optional.of(existingUser));

        User newUser = new User();
        newUser.setUserEmail("test@example.com");

        assertThrows(MeetingConflictException.class, () -> userService.createUser(newUser));
    }

    @Test
    public void testCreateUserThrowsRoleNotFoundExceptionWhenRoleNotFound() {
        User newUser = new User();
        Role newRole = new Role();
        newRole.setRoleName("NON_EXISTING_ROLE");
        newUser.setUserRole(newRole);

        when(roleRepository.findByRoleName(anyString())).thenReturn(Optional.empty());

        assertThrows(RoleNotFoundException.class, () -> userService.createUser(newUser));
    }

    @Test
    public void testFindUserByIdThrowsUserNotFoundExceptionWhenUserNotFound() {
        when(userRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.findUserById(1));
    }
}
