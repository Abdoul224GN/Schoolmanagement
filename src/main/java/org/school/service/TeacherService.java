package org.school.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.school.dto.TeacherRequestDTO;
import org.school.dto.TeacherResponseDTO;
import org.school.entity.Teacher;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.TeacherMapper;
import org.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    TeacherRepository teacherRepository;

    public List<TeacherResponseDTO> getAllTeachers() {
        return teacherRepository.findAll().stream().map(TeacherMapper::toDTO).toList();
    }

    public TeacherResponseDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé"));
        return TeacherMapper.toDTO(teacher);
    }

    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = TeacherMapper.toEntity(teacherRequestDTO);
        return TeacherMapper.toDTO(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher1 = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé"));
        teacher1 = Teacher.builder()
                .id(teacher1.getId())
                .firstName(teacherRequestDTO.firstName())
                .lastName(teacherRequestDTO.lastName())
                .sex(teacherRequestDTO.sex())
                .birthDate(teacherRequestDTO.birthDate())
                .phone(teacherRequestDTO.phone())
                .email(teacherRequestDTO.email())
                .photo(teacherRequestDTO.photo()).build();
        return TeacherMapper.toDTO(teacherRepository.save(teacher1));
    }

    public void deleteTeacher(Long id) {
        teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable"));
        teacherRepository.deleteById(id);
    }
}
