package ru.neoflex.meeting_calendar.entity;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

@Entity

public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Schema(description = "Наименование встречи", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;
    private LocalDateTime;






}
