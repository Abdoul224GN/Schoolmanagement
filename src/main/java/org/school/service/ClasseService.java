package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.ClasseRequestDTO;
import org.school.dto.ClasseResponseDTO;
import org.school.dto.PaginationResponseDTO;
import org.school.entity.Classe;
import org.school.entity.Grade;
import org.school.entity.Teacher;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.ClasseMapper;
import org.school.repository.ClasseRepository;
import org.school.repository.GradeRepository;
import org.school.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClasseService {

    ClasseRepository classeRepository;
    TeacherRepository teacherRepository;
    GradeRepository gradeRepository;
    static final String MESSAGE = "Classe non trouvé";

    public PaginationResponseDTO<ClasseResponseDTO> getAllClasses(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<ClasseResponseDTO> result = classeRepository.findAll(pageable).map(ClasseMapper::toDTO);
        return new PaginationResponseDTO<>(result);
    }

    public ClasseResponseDTO getClassById(Long id) {
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
        classe.setName(classeRequestDTO.name());
        Teacher supervisor = teacherRepository.findById(classeRequestDTO.supervisorId()).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé"));
        classe.setSupervisor(supervisor);
        Grade grade = gradeRepository.findById(classeRequestDTO.gradeId()).orElseThrow(() -> new ResourceNotFoundException("Niveau non trouvé"));
        classe.setGrade(grade);
        return ClasseMapper.toDTO(classeRepository.save(classe));
    }

}
