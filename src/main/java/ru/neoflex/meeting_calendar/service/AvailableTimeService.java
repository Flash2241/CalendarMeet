package ru.neoflex.meeting_calendar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.neoflex.meeting_calendar.entity.AvailableTime;
import ru.neoflex.meeting_calendar.entity.User;
import ru.neoflex.meeting_calendar.exceptions.MeetingConflictException;
import ru.neoflex.meeting_calendar.repo.AvailableTimeRepository;

import java.security.Timestamp;
import java.util.List;

@Service
public class AvailableTimeService {

    private final AvailableTimeRepository availableTimeRepository;

    @Autowired
    public AvailableTimeService(AvailableTimeRepository availableTimeRepository) {
        this.availableTimeRepository = availableTimeRepository;
    }

    public AvailableTime addAvailableTime(AvailableTime availableTime) {
        // Проверка на пересечение доступного времени
        List<AvailableTime> conflictingTimes = availableTimeRepository.findByStartTimeBetween(availableTime.getStartTime(), availableTime.getEndTime());
        if (!conflictingTimes.isEmpty()) {
            throw new MeetingConflictException("The available time conflicts with existing entries");
        }

        return availableTimeRepository.save(availableTime);
    }

    public List<AvailableTime> findAvailableTimesByUser(User user) {
        return availableTimeRepository.findByUser(user);
    }

    public List<AvailableTime> findAvailableTimesWithinPeriod(Timestamp startTime, Timestamp endTime) {
        return availableTimeRepository.findByStartTimeBetween(startTime, endTime);
    }
}

