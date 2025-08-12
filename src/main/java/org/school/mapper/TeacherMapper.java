package org.school.mapper;

import org.school.dto.TeacherRequestDTO;
import org.school.dto.TeacherResponseDTO;
import org.school.entity.Subject;
import org.school.entity.Teacher;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.SubjectRepository;

import java.util.stream.Collectors;

public class TeacherMapper {

    public static TeacherResponseDTO toDTO(Teacher entity) {
        return TeacherResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getFirstName() + " " + entity.getLastName())
                .sex(entity.getSex())
                .birthDate(entity.getBirthDate())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt()).build();
    }

    public static Teacher toEntity(TeacherRequestDTO dto) {
        Teacher teacher = new Teacher();
        teacher.setFirstName(dto.firstName());
        teacher.setLastName(dto.lastName());
        teacher.setSex(dto.sex());
        teacher.setBirthDate(dto.birthDate());
        teacher.setEmail(dto.email());
        teacher.setPhone(dto.phone());
        teacher.setPhoto(dto.photo());
        return teacher;
    }

    public static TeacherResponseDTO toDTOWithSubject(Teacher entity) {
        return TeacherResponseDTO.builder()
                .id(entity.getId())
                .name(entity.getFirstName() + " " + entity.getLastName())
                .sex(entity.getSex())
                .birthDate(entity.getBirthDate())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .photo(entity.getPhoto())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .subjects(entity.getSubjects().stream().map(SubjectMapper::toDTO).collect(Collectors.toSet()))
                .build();
    }

    public static Teacher toEntityWithSubject(TeacherRequestDTO dto, SubjectRepository subjectRepository) {
        Teacher teacher = toEntity(dto);
        for (Long subjectId : dto.subjectIds()) {
            Subject subject = subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Les matière n'existe pas"));
            teacher.addSubject(subject);
        }
        return teacher;
    }
}
