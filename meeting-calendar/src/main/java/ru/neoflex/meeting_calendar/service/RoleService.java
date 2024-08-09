package ru.neoflex.meeting_calendar.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.repo.RoleRepository;

import javax.management.relation.Role;
import java.util.List;
import java.util.Optional;


@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role updateRole(Long id, Role roleDetails) {
        Optional<Role> roleOptional = roleRepository.findById(id);

        if (roleOptional.isPresent()) {
            Role role = roleOptional.get();
            role.setName(roleDetails.getName());
            role.setDescription(roleDetails.getDescription());
            return roleRepository.save(role);
        }

        return null;  // лучше бросать исключение, если роль не найдена
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
