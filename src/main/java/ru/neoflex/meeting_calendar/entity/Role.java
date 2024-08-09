package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Roles")
public class Role {
    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roleId;

    @Column(name = "role_name", nullable = false)
    private String name;

    @Column(name = "role_description")
    private String description;

    // Геттеры и сеттеры
}
// Модель роли пользователя