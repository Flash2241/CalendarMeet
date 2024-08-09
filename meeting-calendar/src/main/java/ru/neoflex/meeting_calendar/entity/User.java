package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.*;

import javax.management.relation.Role;


@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "user_surname", nullable = false)
    private String surname;

    @Column(name = "user_patronymic")
    private String patronymic;

    @Column(name = "user_email", unique = true, nullable = false)
    private String email;

    @Column(name = "user_phone_number", unique = true)
    private String phoneNumber;

    @ManyToOne
    @JoinColumn(name = "user_role")
    private Role role;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;


    // Геттеры и сеттеры
}
// Модель пользователя