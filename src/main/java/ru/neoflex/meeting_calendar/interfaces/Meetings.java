package ru.neoflex.meeting_calendar.interfaces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.neoflex.meeting_calendar.entity.Meeting;

public interface Meetings {

  @Operation(summary = "Получение списка всех встреч",
      description = "Возвращает: Список встреч в формате JSON.")
  List<Meeting> getAllMeetings();

  @Operation(summary = "Получение информации о конкретной встрече по её ID",
      description = "Возвращает: Объект встречи в формате JSON или статус 404, если встреча не найдена.")
  ResponseEntity<Meeting> getMeetingById(@Parameter(name = "Идентификатор встречи") Long id);

  @Operation(summary = "Создание новой встречи",
      description = "Возвращает: Созданный объект встречи с присвоенным идентификатором.")
  ResponseEntity<Meeting> createMeeting(@RequestBody Meeting meeting);

  @Operation(summary = "Обновление информации о встрече",
      description = "Возвращает: Обновленный объект встречи или статус 404, если встреча не найдена.")
  ResponseEntity<Meeting> updateMeeting(@Parameter(name = "Идентификатор встречи") Long id, @RequestBody Meeting meetingDetails);

  @Operation(summary = "Удаление встречи по её ID",
      description = "Возвращает: Статус успешного удаления или статус 404, если встреча не найдена.")
  ResponseEntity<Void> deleteMeeting(@Parameter(name = "Идентификатор встречи") Long id);

}
