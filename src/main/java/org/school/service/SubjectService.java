package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.SubjectRequestDTO;
import org.school.dto.SubjectResponseDTO;
import org.school.entity.Subject;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.SubjectMapper;
import org.school.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubjectService {

    SubjectRepository subjectRepository;

    public List<SubjectResponseDTO> getAllSubjects() {
        return subjectRepository.findAll().stream().map(SubjectMapper::toDTO).toList();
    }

    public SubjectResponseDTO getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La matière n'existe pas"));
        return SubjectMapper.toDTO(subject);
    }

    public SubjectResponseDTO createSubject(SubjectRequestDTO subjectRequestDTO) {
        return SubjectMapper.toDTO(subjectRepository.save(SubjectMapper.toEntity(subjectRequestDTO)));
    }

    public void deleteSubject(Long id) {
        subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La matière n'existe pas"));
        subjectRepository.deleteById(id);
    }

    public SubjectResponseDTO updateSubject(Long id, SubjectRequestDTO subjectRequestDTO) {
        Subject subject = subjectRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("La matière n'existe pas"));
        subject.setName(subjectRequestDTO.name());
        return SubjectMapper.toDTO(subjectRepository.save(subject));
    }
}
