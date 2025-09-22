package org.school.mapper;

import org.school.dto.ScheduleRequestDTO;
import org.school.dto.ScheduleResponseDTO;
import org.school.entity.Schedule;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.ClasseRepository;
import org.school.repository.SubjectRepository;
import org.school.repository.TeacherRepository;

public class ScheduleMapper {
    public static Schedule toEntity(ScheduleRequestDTO dto, ClasseRepository classeRepository, TeacherRepository teacherRepository, SubjectRepository subjectRepository) {
        Schedule schedule = new Schedule();
        schedule.setSubject(subjectRepository.findById(dto.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Subject non trouvé")));
        schedule.setClasse(classeRepository.findById(dto.classeId()).orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée")));
        schedule.setTeacher(teacherRepository.findById(dto.teacherId()).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé")));
        schedule.setDayOfWeek(dto.dayOfWeek());
        schedule.setStartTime(dto.starTime());
        schedule.setEndTime(dto.endTime());
        schedule.setNotes(dto.notes());
        return schedule;
    }

    public static ScheduleResponseDTO fromEntity(Schedule schedule) {
        return new ScheduleResponseDTO(
                schedule.getId(),
                schedule.getSubject() != null ? schedule.getSubject().getId() : null,
                schedule.getSubject() != null ? schedule.getSubject().getName() : null,
                schedule.getClasse() != null ? schedule.getClasse().getId() : null,
                schedule.getClasse() != null ? schedule.getClasse().getName() : null,
                schedule.getTeacher() != null ? schedule.getTeacher().getId() : null,
                schedule.getTeacher() != null ? schedule.getTeacher().getFirstName() + " " + schedule.getTeacher().getLastName() : null,
                schedule.getDayOfWeek(),
                schedule.getStartTime(),
                schedule.getEndTime(),
                schedule.getNotes()
        );
    }
}
