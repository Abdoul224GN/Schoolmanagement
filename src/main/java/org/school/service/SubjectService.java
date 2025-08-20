package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.SubjectRequestDTO;
import org.school.dto.SubjectResponseDTO;
import org.school.entity.Subject;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.SubjectMapper;
import org.school.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubjectService {

    SubjectRepository subjectRepository;

    public PaginationResponseDTO<SubjectResponseDTO> getAllSubjects(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());
        Page<SubjectResponseDTO> result = subjectRepository.findAll(pageable).map(SubjectMapper::toDTO);
        return new PaginationResponseDTO<>(result);
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
