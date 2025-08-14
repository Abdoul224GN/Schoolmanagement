package org.school.mapper;

import org.school.dto.ClasseRequestDTO;
import org.school.dto.ClasseResponseDTO;
import org.school.entity.Classe;
import org.school.entity.Grade;
import org.school.entity.Teacher;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.GradeRepository;
import org.school.repository.TeacherRepository;

public class ClasseMapper {
    public static Classe toEntity(ClasseRequestDTO dto, TeacherRepository teacherRepository, GradeRepository gradeRepository) {
        Classe classe = new Classe();
        classe.setName(dto.name());
        Teacher supervisor = teacherRepository.findById(dto.supervisorId()).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé"));
        classe.setSupervisor(supervisor);
        Grade grade = gradeRepository.findById(dto.gradeId()).orElseThrow(() -> new ResourceNotFoundException("Niveau non trouvé"));
        classe.setGrade(grade);
        return classe;
    }

    public static ClasseResponseDTO toDTO(Classe entity) {
        return ClasseResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .supervisor(TeacherMapper.toDTO(entity.getSupervisor()))
                .grade(GradeMapper.toDTO(entity.getGrade()))
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
