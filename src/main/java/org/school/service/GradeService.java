package org.school.service;

import lombok.AllArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.school.dto.GradeRequestDTO;
import org.school.dto.GradeResponseDTO;
import org.school.entity.Grade;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.GradeMapper;
import org.school.repository.GradeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@DynamicUpdate
public class GradeService {

    GradeRepository gradeRepository;

    public List<GradeResponseDTO> getAllGrade() {
        return gradeRepository.findAll().stream().map(GradeMapper::toDTO).toList();
    }

    public GradeResponseDTO getGradeById(Long id) {
        Optional<Grade> optionalGrade = gradeRepository.findById(id);
        if (optionalGrade.isEmpty()) {
            throw new ResourceNotFoundException("Le niveau n'existe pas");
        }
        return GradeMapper.toDTO(optionalGrade.get());
    }

    public GradeResponseDTO createGrade(GradeRequestDTO gradeRequestDTO) {
        return GradeMapper.toDTO(gradeRepository.save(GradeMapper.toEntity(gradeRequestDTO)));
    }

    public GradeResponseDTO updateGrade(Long id, GradeRequestDTO gradeRequestDTO) {
        Grade grade = gradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Le niveau n'existe pas"));
        grade.setName(gradeRequestDTO.name());
        return GradeMapper.toDTO(gradeRepository.save(grade));
    }

    public void deleteGrade(Long id) {
        Optional<Grade> grade = gradeRepository.findById(id);
        if (grade.isPresent()) {
            gradeRepository.deleteById(id);
        } else throw new ResourceNotFoundException("Le niveau n'existe pas");

    }
}
