package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.ParentRequestDTO;
import org.school.dto.ParentResponseDTO;
import org.school.entity.Parent;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.ParentMapper;
import org.school.repository.ParentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ParentService {
    private final FileStorageService fileStorageService;
    ParentRepository parentRepository;

    public PaginationResponseDTO<ParentResponseDTO> getAllParents(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ParentResponseDTO> result = parentRepository.findAll(pageable).map(ParentMapper::toDTO);
        return new PaginationResponseDTO<>(result);
    }

    public PaginationResponseDTO<ParentResponseDTO> searchByName(String name) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<ParentResponseDTO> result = parentRepository
                .findByFirstNameContainingIgnoreCaseOrderByFirstNameAsc(name, pageable)
                .map(ParentMapper::toDTO);
        return new PaginationResponseDTO<>(result);
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
        return ParentMapper.toDTO(parentRepository.save(parent));
    }

    public ParentResponseDTO uploadPhoto(Long id, MultipartFile file) throws IOException {
        Parent parent = parentRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Parent non trouvé"));
        String url = fileStorageService.storeAndGetUrl(file);
        return ParentMapper.toDTO(parentRepository.save(parent));
    }
}
