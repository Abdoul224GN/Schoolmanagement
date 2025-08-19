package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.StudentRequestDTO;
import org.school.dto.StudentResponseDTO;
import org.school.entity.Parent;
import org.school.entity.ParentEleve;
import org.school.entity.Student;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.StudentMapper;
import org.school.repository.ClasseRepository;
import org.school.repository.ParentEleveRepository;
import org.school.repository.ParentRepository;
import org.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;
    private final ClasseRepository classeRepository;
    private final ParentEleveRepository parentEleveRepository;

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toResponseDTO)
                .toList();
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Élève non trouvé"));
        return StudentMapper.toResponseDTO(student);
    }

    public StudentResponseDTO createStudent(StudentRequestDTO dto) {
        Student student = StudentMapper.toEntity(dto, classeRepository, parentRepository);
        Student saved = studentRepository.save(student);
        return StudentMapper.toResponseDTO(saved);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Élève non trouvé"));
        studentRepository.delete(student);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO dto) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Élève non trouvé"));
        student.setFirstName(dto.firstName());
        student.setLastName(dto.lastName());
        student.setSex(dto.sex());
        student.setAddress(dto.address());
        student.setPhoto(dto.photo());
        student.setBirthDate(dto.birthDate());
        student.setClasse(classeRepository.findById(dto.classeId()).orElseThrow(() -> new ResourceNotFoundException("Classe non trouvée")));
        parentEleveRepository.deleteAll(student.getParentRelations());
        student.getParentRelations().clear();

        dto.parentRelations().forEach(rel -> {
            Parent parent = parentRepository.findById(rel.parentId())
                    .orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
            ParentEleve pe = new ParentEleve();
            pe.setStudent(student);
            pe.setParent(parent);
            pe.setTypeRelation(rel.typeRelation());
            student.getParentRelations().add(pe);
        });

        studentRepository.save(student);
        return StudentMapper.toResponseDTO(student);
    }
}
