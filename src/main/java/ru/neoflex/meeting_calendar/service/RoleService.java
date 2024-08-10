package ru.neoflex.meeting_calendar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Role;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.RoleRepository;
import ru.neoflex.meeting_calendar.exceptions.RoleNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findRoleByName(String roleName) {
        return roleRepository.findByRoleName(roleName)
                .orElseThrow(() -> new RoleNotFoundException("Role not found"));
    }

    public Role createRole(Role role) {
        if (roleRepository.findByRoleName(role.getRoleName()).isPresent()) {
            throw new MeetingConflictException("Role with this name already exists");
        }
        return roleRepository.save(role);
    }
}
