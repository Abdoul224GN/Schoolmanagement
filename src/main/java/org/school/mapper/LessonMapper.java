package org.school.mapper;

import org.school.dto.LessonRequestDTO;
import org.school.dto.LessonResponseDTO;
import org.school.entity.Lesson;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.SubjectRepository;

public class LessonMapper {
    public static LessonResponseDTO toDTO(Lesson lesson) {
        return LessonResponseDTO.builder()
                .id(lesson.getId())
                .name(lesson.getName())
                .day(lesson.getDay())
                .startTime(lesson.getStartTime())
                .endTime(lesson.getEndTime())
                .createdAt(lesson.getCreatedAt())
                .subject(SubjectMapper.toDTO(lesson.getSubject()))
                .updatedAt(lesson.getUpdatedAt()).build();
    }

    public static Lesson toEntity(LessonRequestDTO dto, SubjectRepository subjectRepository) {
        Lesson lesson = new Lesson();
        lesson.setName(dto.name());
        lesson.setDay(dto.day());
        lesson.setStartTime(dto.startTime());
        lesson.setEndTime(dto.endTime());
        lesson.setSubject(subjectRepository.findById(dto.subjectId()).orElseThrow(()-> new ResourceNotFoundException("Leçon ")));
        return lesson;
    }

}
