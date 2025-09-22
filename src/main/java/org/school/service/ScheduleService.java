package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.ScheduleRequestDTO;
import org.school.dto.ScheduleResponseDTO;
import org.school.entity.Schedule;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.ScheduleMapper;
import org.school.repository.ClasseRepository;
import org.school.repository.ScheduleRepository;
import org.school.repository.SubjectRepository;
import org.school.repository.TeacherRepository;
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

    private final ScheduleRepository scheduleRepository;
    private final ClasseRepository classeRepository;
    private final TeacherRepository teacherRepository;
    private final SubjectRepository subjectRepository;

    public List<Map<String, Object>> getScheduleForClasse(Long classeId) {
        List<Schedule> schedules = scheduleRepository.findByClasseId(classeId);

        return schedules.stream().map(s -> {
            Map<String, Object> event = new HashMap<>();
            LocalDate nextDay = LocalDate.now().with(TemporalAdjusters.nextOrSame(s.getDayOfWeek()));
            event.put("id", s.getId());
            event.put("title", s.getSubject().getName());
            event.put("dayOfWeek", s.getDayOfWeek());
            event.put("teacher", s.getTeacher().getFirstName() + " " + s.getTeacher().getLastName());
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
            event.put("dayOfWeek", s.getDayOfWeek());
            event.put("classe", s.getClasse().getName());
            event.put("start", LocalDateTime.of(nextDay, s.getStartTime()));
            event.put("end", LocalDateTime.of(nextDay, s.getEndTime()));
            return event;
        }).toList();
    }

    public ScheduleResponseDTO addSchedule(ScheduleRequestDTO scheduleRequestDTO) {
        Schedule schedule = ScheduleMapper.toEntity(scheduleRequestDTO, classeRepository, teacherRepository, subjectRepository);
        return ScheduleMapper.fromEntity(scheduleRepository.save(schedule));
    }


    public Schedule updateSchedule(Long id, ScheduleRequestDTO scheduleRequestDTO) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emploi du temps non trouvé avec l'ID : " + id));
        existingSchedule.setSubject(subjectRepository.findById(scheduleRequestDTO.subjectId())
                .orElseThrow(() -> new ResourceNotFoundException("Subject non trouvé")));
        existingSchedule.setClasse(classeRepository.findById(scheduleRequestDTO.classeId())
                .orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée")));
        existingSchedule.setTeacher(teacherRepository.findById(scheduleRequestDTO.teacherId())
                .orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé")));
        existingSchedule.setDayOfWeek(scheduleRequestDTO.dayOfWeek());
        existingSchedule.setStartTime(scheduleRequestDTO.starTime());
        existingSchedule.setEndTime(scheduleRequestDTO.endTime());
        existingSchedule.setNotes(scheduleRequestDTO.notes());

        return scheduleRepository.save(existingSchedule);
    }

    public void deleteSchedule(Long id) {
        scheduleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Emplois du temps non trouvé"));
        scheduleRepository.deleteById(id);
    }
}
