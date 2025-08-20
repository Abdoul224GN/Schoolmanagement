package org.school.mapper;

import org.school.dto.PresenceRequestDTO;
import org.school.dto.PresenceResponseDTO;
import org.school.entity.Lesson;
import org.school.entity.Presence;
import org.school.entity.Student;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.LessonRepository;
import org.school.repository.StudentRepository;

public class PresenceMapper {
    public static PresenceResponseDTO toDTO(Presence presence) {
        return PresenceResponseDTO.builder()
                .id(presence.getId())
                .isPresent(presence.getIsPresent())
                .comment(presence.getComment())
                .student(StudentMapper.toResponseDTO(presence.getStudent()))
                .lesson(LessonMapper.toDTO(presence.getLesson()))
                .build();
    }

    public static Presence toEntity(PresenceRequestDTO dto, StudentRepository studentRepository, LessonRepository lessonRepository) {
        Presence presence = new Presence();
        presence.setIsPresent(dto.isPresent());
        presence.setComment(dto.comment());
        Student student = studentRepository.findById(dto.studentId()).orElseThrow(() -> new ResourceNotFoundException("Elève non trouvé"));
        presence.setStudent(student);
        Lesson lesson = lessonRepository.findById(dto.lessonID()).orElseThrow(()-> new ResourceNotFoundException("Lesson non trouvé"));
        presence.setLesson(lesson);
        return presence;
    }
}
