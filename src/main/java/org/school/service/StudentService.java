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
import java.util.Set;
import java.util.stream.Collectors;

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
        studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Elève non trouvé"));
        studentRepository.deleteById(id);
    }

    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Elève non trouvé"));
        student.setFirstName(studentDTO.firstName());
        student.setLastName(studentDTO.lastName());
        student.setSex(studentDTO.sex());
        student.setAddress(studentDTO.address());
        student.setPhoto(studentDTO.photo());
        student.setBirthDate(studentDTO.birthDate());
        student.setClasse(classeRepository.findById(studentDTO.classeId()).orElseThrow(() -> new ResourceNotFoundException("Classe non trouvé")));
        Set<Parent> parents = studentDTO.parentIds().stream().map(parentId -> parentRepository.findById(parentId)
                    .orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"))).collect(Collectors.toSet());
        student.getParents().forEach(parent -> parent.getStudents().remove(student));
        student.getParents().clear();
        parents.forEach(student::addParent);
        studentRepository.save(student);
        return StudentMapper.toResponseDTO(student);
    }
}
