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
        student.setClasse(classeRepository.findById(dto.classeId()).orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée")));
        for (Long id : dto.parentIds()) {
            Parent parent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Parent introuvable"));
            student.addParent(parent);
        }
        return student;
    }

    public static StudentResponseDTO toResponseDTO(Student student) {
        Set<ParentResponseDTO> mappedParents = student.getParents().stream()
                .map(ParentMapper::toDTO)
                .collect(Collectors.toSet());

        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getFirstName() + " " + student.getLastName())
                .sex(student.getSex())
                .birthDate(student.getBirthDate())
                .address(student.getAddress())
                .photo(student.getPhoto())
                .classe(ClasseMapper.toDTO(student.getClasse()))
                .parents(mappedParents)
                .build();
    }
}
