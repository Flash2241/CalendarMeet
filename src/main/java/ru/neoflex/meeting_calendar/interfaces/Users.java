package ru.neoflex.meeting_calendar.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.meeting_calendar.entity.User;

public interface Users {

  @Operation(summary = "Регистрация пользователя",
  description = "Создаёт нового пользователя с указанной ролью и возвращает уведомление.")
  ResponseEntity<String> registerUser(@RequestBody User user);

  @Operation(summary = "Авторизация пользователя",
      description = "Производит процесс авторизации пользователя и возвращает уведомление.")
  ResponseEntity<String> loginUser(@RequestBody Map<String, String> loginData);

  @Operation(summary = "Получение списка пользователей по роли",
      description = "Возвращает список пользователей по их роли через идентификатор роли")
  ResponseEntity<List<User>> getUsersByRole(Integer roleId);
}
