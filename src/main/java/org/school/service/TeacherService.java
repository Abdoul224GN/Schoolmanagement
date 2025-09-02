package org.school.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.TeacherRequestDTO;
import org.school.dto.TeacherResponseDTO;
import org.school.entity.Subject;
import org.school.entity.Teacher;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.TeacherMapper;
import org.school.repository.SubjectRepository;
import org.school.repository.TeacherRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TeacherService {

    TeacherRepository teacherRepository;
    SubjectRepository subjectRepository;
    FileStorageService fileStorageService;

    public PaginationResponseDTO<TeacherResponseDTO> getAllTeachers(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<TeacherResponseDTO> result = teacherRepository.findAll(pageable).map(TeacherMapper::toDTO);
        return new PaginationResponseDTO<>(result);
    }

    public PaginationResponseDTO<TeacherResponseDTO> searchByName(String name) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<TeacherResponseDTO> result = teacherRepository
                .findByFirstNameContainingIgnoreCaseOrderByFirstNameAsc(name, pageable)
                .map(TeacherMapper::toDTO);

        return new PaginationResponseDTO<>(result);
    }

    public TeacherResponseDTO getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé"));
        return TeacherMapper.toDTOWithSubject(teacher);
    }

    public TeacherResponseDTO createTeacher(TeacherRequestDTO teacherRequestDTO) {
        Teacher teacher = TeacherMapper.toEntityWithSubject(teacherRequestDTO, subjectRepository);
        return TeacherMapper.toDTOWithSubject(teacherRepository.save(teacher));
    }

    @Transactional
    public TeacherResponseDTO updateTeacher(Long id, TeacherRequestDTO dto) {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvé"));
        teacher.setFirstName(dto.firstName());
        teacher.setLastName(dto.lastName());
        teacher.setSex(dto.sex());
        teacher.setBirthDate(dto.birthDate());
        teacher.setPhone(dto.phone());
        teacher.setEmail(dto.email());
        teacher.setPhoto(dto.photo());
        Set<Subject> updatedSubjects = dto.subjectIds().stream()
                .map(subjectId -> subjectRepository.findById(subjectId).orElseThrow(() -> new ResourceNotFoundException("Matière introuvable : " + subjectId)))
                .collect(Collectors.toSet());
        teacher.getSubjects().forEach(subject -> subject.getTeachers().remove(teacher));
        teacher.getSubjects().clear();
        updatedSubjects.forEach(teacher::addSubject);
        Teacher saved = teacherRepository.save(teacher);
        return TeacherMapper.toDTOWithSubject(saved);
    }

    public void deleteTeacher(Long id) {
        teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant introuvable"));
        teacherRepository.deleteById(id);
    }

    public TeacherResponseDTO uploadPhoto(Long id, MultipartFile file) throws IOException {
        Teacher teacher = teacherRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Enseignant non trouvable"));
        String url = fileStorageService.storeAndGetUrl(file);
        teacher.setPhoto(url);
        return TeacherMapper.toDTOWithSubject(teacherRepository.save(teacher));
    }
}
