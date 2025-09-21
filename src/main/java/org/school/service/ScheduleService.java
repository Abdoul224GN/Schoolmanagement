package org.school.service;

import lombok.AllArgsConstructor;
import org.school.entity.Schedule;
import org.school.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ScheduleService {

    ScheduleRepository scheduleRepository;

    public List<Map<String, Object>> getScheduleForClasse(Long classeId) {
        List<Schedule> schedules = scheduleRepository.findByClasseId(classeId);

        return schedules.stream().map(s -> {
            Map<String, Object> event = new HashMap<>();
            LocalDate nextDay = LocalDate.now().with(TemporalAdjusters.nextOrSame(s.getDayOfWeek()));
            event.put("title", s.getSubject().getName());
            event.put("start", LocalDateTime.of(nextDay, s.getStartTime()));
            event.put("end", LocalDateTime.of(nextDay, s.getEndTime()));
            return event;
        }).toList();
    }

    public List<Map<String, Object>> getScheduleForTeacher(Long teacherId) {
        List<Schedule> schedules = scheduleRepository.findByTeacherId(teacherId);

        return schedules.stream().map(s -> {
            Map<String, Object> event = new HashMap<>();
            LocalDate nextDay = LocalDate.now().with(TemporalAdjusters.nextOrSame(s.getDayOfWeek()));
            event.put("title", s.getSubject().getName());
            event.put("start", LocalDateTime.of(nextDay, s.getStartTime()));
            event.put("end", LocalDateTime.of(nextDay, s.getEndTime()));
            return event;
        }).toList();
    }


}
