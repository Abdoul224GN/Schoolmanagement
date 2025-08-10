package org.school.mapper;

import org.school.dto.TeacherRequestDTO;
import org.school.dto.TeacherResponseDTO;
import org.school.entity.Teacher;

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
}
