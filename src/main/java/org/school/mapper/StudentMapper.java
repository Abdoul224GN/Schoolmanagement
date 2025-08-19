package org.school.mapper;

import org.school.dto.ParentRelationResponseDTO;
import org.school.dto.StudentRequestDTO;
import org.school.dto.StudentResponseDTO;
import org.school.entity.Parent;
import org.school.entity.ParentEleve;
import org.school.entity.Student;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.ClasseRepository;
import org.school.repository.ParentRepository;

public class StudentMapper {

    public static StudentResponseDTO toResponseDTO(Student student) {
        return StudentResponseDTO.builder()
                .id(student.getId())
                .name(student.getFirstName() + " " + student.getLastName())
                .sex(student.getSex())
                .address(student.getAddress())
                .photo(student.getPhoto())
                .birthDate(student.getBirthDate())
                .classe(ClasseMapper.toDTO(student.getClasse()))
                .parentRelations(
                        student.getParentRelations().stream()
                                .map(rel -> new ParentRelationResponseDTO(ParentMapper.toDTO(rel.getParent()), rel.getTypeRelation()))
                                .toList())
                .build();
    }

    public static Student toEntity(StudentRequestDTO dto, ClasseRepository classeRepository, ParentRepository parentRepository) {
        Student student = new Student();
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setSex(dto.sex());
        student.setAddress(dto.address());
        student.setPhoto(dto.photo());
        student.setBirthDate(dto.birthDate());
        student.setClasse(classeRepository.findById(dto.classeId()).orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée")));

        dto.parentRelations().forEach(rel -> {
            Parent parent = parentRepository.findById(rel.parentId()).orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
            ParentEleve pe = new ParentEleve();
            pe.setStudent(student);
            pe.setParent(parent);
            pe.setTypeRelation(rel.typeRelation());
            student.getParentRelations().add(pe);
        });
        return student;
    }
}

