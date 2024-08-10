package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import lombok.Data;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "user_surname", nullable = false, length = 100)
    private String userSurname;

    @Column(name = "user_patronymic", length = 100)
    private String userPatronymic;

    @Column(name = "user_email", nullable = false, unique = true, length = 254)
    private String userEmail;

    @Column(name = "user_phone_number", nullable = false, unique = true, length = 15)
    private String userPhoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_role", nullable = false)
    private Role userRole;

    @Column(nullable = false, unique = true, length = 255)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    // Геттеры и сеттеры
}

// Модель пользователя