package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.LessonRequestDTO;
import org.school.dto.LessonResponseDTO;
import org.school.dto.PaginationResponseDTO;
import org.school.entity.Lesson;
import org.school.entity.Subject;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.LessonMapper;
import org.school.repository.LessonRepository;
import org.school.repository.SubjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LessonService {

    LessonRepository lessonRepository;
    SubjectRepository subjectRepository;

    static final String MESSAGE = "Leçon non trouvé";

    public PaginationResponseDTO<LessonResponseDTO> getAllLessons(Integer page, Integer size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<LessonResponseDTO> result = lessonRepository.findAll(pageable).map(LessonMapper::toDTO);
        return new PaginationResponseDTO<>(result);
    }

    public LessonResponseDTO getLessonById(Long id) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
        return LessonMapper.toDTO(lesson);
    }

    public LessonResponseDTO createLesson(LessonRequestDTO lessonRequestDTO) {
        Lesson lesson = LessonMapper.toEntity(lessonRequestDTO, subjectRepository);
        return LessonMapper.toDTO(lessonRepository.save(lesson));
    }

    public void deleteLesson(Long id) {
        lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
        lessonRepository.deleteById(id);
    }

    public LessonResponseDTO updateLesson(Long id, LessonRequestDTO lessonRequestDTO) {
        Lesson lesson = lessonRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
        lesson.setName(lessonRequestDTO.name());
        lesson.setDay(lessonRequestDTO.day());
        lesson.setStartTime(lessonRequestDTO.startTime());
        lesson.setEndTime(lessonRequestDTO.endTime());
        Subject subject = subjectRepository.findById(lessonRequestDTO.subjectId()).orElseThrow(() -> new ResourceNotFoundException(MESSAGE));
        lesson.setSubject(subject);
        return LessonMapper.toDTO(lessonRepository.save(lesson));
    }

}
