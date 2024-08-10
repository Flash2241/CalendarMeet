package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Column(name = "role_name", nullable = false, length = 10)
    private String roleName;

    @Column(name = "role_description")
    private String roleDescription;

    public String getRoleName() {
        return null;
    }

    public void setRoleName(String nonExistingRole) {
    }

    // Геттеры и сеттеры
}

// Модель роли пользователя