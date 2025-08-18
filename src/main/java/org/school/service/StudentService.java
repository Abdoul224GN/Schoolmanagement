package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.StudentRequestDTO;
import org.school.dto.StudentResponseDTO;
import org.school.entity.Parent;
import org.school.entity.Student;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.StudentMapper;
import org.school.repository.ClasseRepository;
import org.school.repository.ParentRepository;
import org.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StudentService {

    private final ParentRepository parentRepository;
    StudentRepository studentRepository;
    ClasseRepository classeRepository;

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll().stream().map(StudentMapper::toResponseDTO).toList();
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Elève non trouvé"));
        return StudentMapper.toResponseDTO(studentRepository.save(student));
    }

    public StudentResponseDTO createStudent(StudentRequestDTO studentDTO) {
        Student savedStudent = studentRepository.save(StudentMapper.toEntity(studentDTO, classeRepository, parentRepository));
        return StudentMapper.toResponseDTO(savedStudent);
    }

    public void deleteStudent(Long id) {
        studentRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Elève non trouvé"));
        studentRepository.deleteById(id);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Elève non Trouvé"));
        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setSex(studentDTO.sex());
        student.setBirthDate(studentDTO.birthDate());
        student.setAddress(studentDTO.address());
        student.setPhoto(studentDTO.photo());

        if (studentDTO.classeId() != null) {
            classeRepository.findById(studentDTO.classeId()).ifPresent(student::setClasse);
        }

        // Parents
        student.getParents().clear(); // 🔥 méthode que tu dois avoir pour vider les anciennes relations
        if (studentDTO.parents() != null) {
            studentDTO.parents().forEach(rel -> {
                Parent parent = parentRepository.findById(rel.parentId())
                        .orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
                student.addParent(parent, rel.relationshipType());
            });
        }

        Student updated = studentRepository.save(student);
        return StudentMapper.toResponseDTO(updated);
    }
}
