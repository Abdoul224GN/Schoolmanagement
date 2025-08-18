package org.school.mapper;

import org.school.dto.ParentResponseDTO;
import org.school.dto.StudentRequestDTO;
import org.school.dto.StudentResponseDTO;
import org.school.entity.Parent;
import org.school.entity.Student;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.ClasseRepository;
import org.school.repository.ParentRepository;

import java.util.Set;
import java.util.stream.Collectors;

public class StudentMapper {

    public static Student toEntity(StudentRequestDTO dto, ClasseRepository classeRepository, ParentRepository parentRepository) {
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setSex(dto.sex());
        student.setBirthDate(dto.birthDate());
        student.setAddress(dto.address());
        student.setPhoto(dto.photo());

        if (dto.classeId() != null) {
            classeRepository.findById(dto.classeId()).ifPresent(student::setClasse);
        }

        if (dto.parents() != null) {
            dto.parents().forEach(rel -> {
                Parent parent = parentRepository.findById(rel.parentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
                student.addParent(parent, rel.relationshipType());
            });
        }

        return student;
    }

    public static StudentResponseDTO toResponseDTO(Student student) {
        Set<ParentResponseDTO> parents = student.getParents().stream()
                .map(StudentParentMapper::toParentResponseDTO)
                .collect(Collectors.toSet());

        return new StudentResponseDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getSex(),
                student.getBirthDate(),
                student.getAddress(),
                student.getPhoto(),
                ClasseMapper.toDTO(student.getClasse()),
                parents
        );
    }
}
