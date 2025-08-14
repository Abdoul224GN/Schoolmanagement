package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.ClasseRequestDTO;
import org.school.dto.ClasseResponseDTO;
import org.school.entity.Classe;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.ClasseMapper;
import org.school.repository.ClasseRepository;
import org.school.repository.GradeRepository;
import org.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ClasseService {

    ClasseRepository classeRepository;
    TeacherRepository teacherRepository;
    GradeRepository gradeRepository;
    static final String MESSAGE = "Classe non trouvé";

    public List<ClasseResponseDTO> getAllClasses() {
        return classeRepository.findAll().stream().map(ClasseMapper::toDTO).toList();
    }

    public ClasseResponseDTO getLessonById(Long id) {
        return ClasseMapper.toDTO(classeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE)));
    }

    public ClasseResponseDTO createClasse(ClasseRequestDTO classeRequestDTO) {
        Classe classe = ClasseMapper.toEntity(classeRequestDTO, teacherRepository, gradeRepository);
        return ClasseMapper.toDTO(classeRepository.save(classe));
    }

    public void deleteClasse(Long id) {
        classeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
        classeRepository.deleteById(id);
    }

    public ClasseResponseDTO updateClasse(Long id, ClasseRequestDTO classeRequestDTO) {
        Classe classe = classeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
        ClasseMapper.toEntity(classeRequestDTO, teacherRepository, gradeRepository);
        return ClasseMapper.toDTO(classeRepository.save(classe));
    }

}
