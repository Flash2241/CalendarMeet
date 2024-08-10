package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "roles")
public class Role {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "role_name", nullable = false, length = 10)
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    // Геттеры и сеттеры
}

// Модель роли пользователя