package ru.neoflex.meeting_calendar.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.neoflex.meeting_calendar.entity.Role;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.interfaces.Users;
import ru.neoflex.meeting_calendar.service.RoleService;
import ru.neoflex.meeting_calendar.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        if (userService.findUserByEmail(user.getUserEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
        }

        Role userRole = roleService.findRoleByName(user.getUserRole().getRoleName());
        user.setUserRole(userRole);
        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody Map<String, String> loginData, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }

        String username = loginData.get("username");
        String password = loginData.get("password");

        Optional<User> userOpt = userService.findUserByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            // Логика проверки пароля (пароль должен быть хеширован и проверен корректно)
            if (user.getPassword().equals(password)) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid credentials");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    // Другие методы
}


