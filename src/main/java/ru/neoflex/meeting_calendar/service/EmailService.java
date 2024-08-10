
package ru.neoflex.meeting_calendar.service;

import java.time.format.DateTimeFormatter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.Meeting;
import ru.neoflex.meeting_calendar.entity.MeetingParticipant;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.repo.ParticipantRepository;

@Service
@Slf4j
@RequiredArgsConstructor
public class EmailService {

  private final JavaMailSender emailSender;

  private final ParticipantRepository participantRepository;
  private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
  private final DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

  public void sendEmailInvitations(Meeting meeting) {
    List<MeetingParticipant> participants = participantRepository.findByParticipantData_Meeting_MeetingId(
        meeting.getMeetingId());

    String meetingDate = meeting.getStartTime().toLocalDate().format(dateFormatter);
    String meetingTime = meeting.getStartTime().toLocalTime().format(timeFormatter);

    SimpleMailMessage message = new SimpleMailMessage();
    message.setSubject("Запись на собеседование " + meetingDate);

    for (MeetingParticipant meetingParticipant : participants) {
      User participant = meetingParticipant.getParticipantData().getUser();

      message.setTo(participant.getEmail());
      message.setText(
          "Вы записаны на собеседование в качестве " + participant.getRole().getName() + "."
              + "Собеседование назначено на " + meetingDate + ", " + meetingTime + ".");

      //TODO: принятие или отказ - ссылка на страницу встречу фронтенда,
      // где можно будет принять решение?
      emailSender.send(message);
      logSimpleMailMessage(message);
    }

  }

  public void sendUpdateEmailNotification(Meeting meeting, Meeting oldMeeting) {

    String meetingDate = meeting.getStartTime().toLocalDate().format(dateFormatter);
    String oldMeetingDate = oldMeeting.getStartTime().toLocalDate().format(timeFormatter);

    String meetingTime = meeting.getStartTime().toLocalTime().format(timeFormatter);
    String oldMeetingTime = meeting.getStartTime().toLocalTime().format(timeFormatter);

    List<MeetingParticipant> participants = participantRepository.findByParticipantData_Meeting_MeetingId(
        meeting.getMeetingId());

    List<MeetingParticipant> oldParticipants = participantRepository.findByParticipantData_Meeting_MeetingId(
        oldMeeting.getMeetingId());
    SimpleMailMessage message = new SimpleMailMessage();
    String emailText = "";

    for(MeetingParticipant participant : participants) {
      for(MeetingParticipant oldParticipant : oldParticipants) {
        User participantId = participant.getParticipantData().getUser();
        User oldParticipantId = oldParticipant.getParticipantData().getUser();

        if(!(participantId.getUserId().equals(oldParticipantId.getUserId()))) {
          message.setSubject("Изменён состав участников собеседования " + meetingDate + " изменено");
          emailText += participantId.getRole().getName() + " " + participantId.getName() + " изменился: теперь"
              + " данную роль занимает " + participantId.getName() + ".\n";
        }
      }
    }

    if (!meetingTime.equals(oldMeetingTime)) {
      message.setSubject("Время начала собеседования " + meetingDate + " изменено");
      emailText += "Время начала собеседования "
          + "было перенесено организатором с " + oldMeetingTime + " на " + meetingTime + ".\n";
    }

    if(!meetingDate.equals(oldMeetingDate)) {
      message.setSubject("Собеседование " + oldMeetingDate + " перенесено");
      emailText += "Собеседование было перенесено организатором с " + oldMeetingDate + " на " + meetingDate + ".\n";
    }

    for(MeetingParticipant participant : participants) {

      message.setTo(participant.getParticipantData().getUser().getEmail());
      emailSender.send(message);
      logSimpleMailMessage(message);
    }
  }


  private void logSimpleMailMessage(SimpleMailMessage message) {
    log.debug("Отправлено письмо по адресу {}. Тема: {}",
        message.getTo(),
        message.getSubject());
  }
}

