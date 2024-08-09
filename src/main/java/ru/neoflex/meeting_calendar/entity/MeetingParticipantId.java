package ru.neoflex.meeting_calendar.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.io.Serializable;
import java.util.Objects;
import lombok.Data;

@Embeddable
@Data
public class MeetingParticipantId implements Serializable {

  @OneToOne
  @JoinColumn(name = "meeting_id")
  private Meeting meeting;

  @OneToOne
  @JoinColumn(name = "user_id")
  private User user;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MeetingParticipantId that = (MeetingParticipantId) o;
    return Objects.equals(meeting, that.meeting) &&
        Objects.equals(user, that.user);
  }

  @Override
  public int hashCode() {
    return Objects.hash(meeting, user);
  }
}

