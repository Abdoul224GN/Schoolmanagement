package org.school.mapper;

import org.school.dto.LessonRequestDTO;
import org.school.dto.LessonResponseDTO;
import org.school.entity.Lesson;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.SubjectRepository;
import org.school.repository.ClasseRepository;

public class LessonMapper {
    public static LessonResponseDTO toDTO(Lesson lesson) {
        return LessonResponseDTO.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .day(lesson.getDay())
                .classeId(lesson.getClasse().getId())
                .classe(lesson.getClasse().getName())
                .startTime(lesson.getStartTime())
                .endTime(lesson.getEndTime())
                .createdAt(lesson.getCreatedAt())
                .subjectId(lesson.getSubject().getId())
                .subject(lesson.getSubject().getName())
                .updatedAt(lesson.getUpdatedAt()).build();
    }

    public static Lesson toEntity(LessonRequestDTO dto, SubjectRepository subjectRepository, ClasseRepository classeRepository) {
        Lesson lesson = new Lesson();
        lesson.setName(dto.name());
        lesson.setDay(dto.day());
        lesson.setStartTime(dto.startTime());
        lesson.setEndTime(dto.endTime());
        lesson.setSubject(subjectRepository.findById(dto.subjectId()).orElseThrow(() -> new ResourceNotFoundException("Leçon ")));
        lesson.setClasse(classeRepository.findById(dto.classeId()).orElseThrow(() -> new ResourceNotFoundException("Classe non trouvé ")));
        return lesson;
    }

}
