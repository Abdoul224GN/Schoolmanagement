package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.ParentRequestDTO;
import org.school.dto.ParentResponseDTO;
import org.school.entity.Parent;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.ParentMapper;
import org.school.repository.ParentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParentService {
    ParentRepository parentRepository;

    public List<ParentResponseDTO> getAllParents() {
        return parentRepository.findAll().stream().map(ParentMapper::toDTO).toList();
    }

    public ParentResponseDTO getParentById(Long id) {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
        return ParentMapper.toDTO(parent);
    }

    public ParentResponseDTO createParent(ParentRequestDTO parentRequestDTO) {
        return ParentMapper.toDTO(parentRepository.save(ParentMapper.toEntity(parentRequestDTO)));
    }

    public void deleteParent(Long id) {
        parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
        parentRepository.deleteById(id);
    }

    public ParentResponseDTO updateParent(Long id, ParentRequestDTO parentRequestDTO) {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
        parent.setFirstName(parentRequestDTO.firstName());
        parent.setLastName(parentRequestDTO.lastName());
        parent.setPhone(parentRequestDTO.phone());
        parent.setPhoto(parentRequestDTO.photo());
        return ParentMapper.toDTO(parentRepository.save(parent));
    }
}
