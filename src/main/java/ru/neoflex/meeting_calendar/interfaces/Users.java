package ru.neoflex.meeting_calendar.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.meeting_calendar.entity.User;

public interface Users {

  @Operation(summary = "Получение списка всех пользователей",
      description = "Возвращает: Список пользователей в формате JSON.")
  List<User> getAllUsers();

  @Operation(summary = "Получение информации о конкретном пользователе по его ID",
      description = "Объект пользователя в формате JSON или статус 404, если пользователь не найден.")
  ResponseEntity<User> getUserById(@Parameter(name = "Идентификатор пользователя") Long id);

  @Operation(summary = "Создание нового пользователя",
      description = "Возвращает: Созданный объект пользователя с присвоенным идентификатором.")
  ResponseEntity<User> createUser(@RequestBody User user);

  @Operation(summary = "Обновление информации о пользователе",
      description = "Возвращает: Обновленный объект пользователя в формате JSON или статус 404, если пользователь не найден.")
  ResponseEntity<User> updateUser(@Parameter(name = "Идентификатор пользователя") Long id, @RequestBody User userDetails);

  @Operation(summary = "Удаление пользователя по его ID",
      description = "Возвращает: Статус успешного удаления или статус 404, если пользователь не найден.")
  ResponseEntity<Void> deleteUser(@Parameter(name = "Идентификатор пользователя") Long id);
}
