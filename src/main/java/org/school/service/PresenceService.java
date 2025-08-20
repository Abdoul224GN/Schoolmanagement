package org.school.service;

import lombok.AllArgsConstructor;
import org.school.dto.PresenceRequestDTO;
import org.school.dto.PresenceResponseDTO;
import org.school.entity.Lesson;
import org.school.entity.Presence;
import org.school.entity.Student;
import org.school.exception.ResourceNotFoundException;
import org.school.mapper.PresenceMapper;
import org.school.repository.LessonRepository;
import org.school.repository.PresenceRepository;
import org.school.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PresenceService {

    PresenceRepository presenceRepository;
    StudentRepository studentRepository;
    LessonRepository lessonRepository;

    public List<PresenceResponseDTO> getAllPresences() {
        return presenceRepository.findAll().stream().map(PresenceMapper::toDTO).toList();
    }

    public PresenceResponseDTO getPresenceById(Long id) {
        return PresenceMapper.toDTO(presenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Presence non trouvé")));
    }

    public PresenceResponseDTO createPresence(PresenceRequestDTO presenceRequestDTO) {
        Presence savedPresence = presenceRepository.save(PresenceMapper.toEntity(presenceRequestDTO, studentRepository, lessonRepository));
        return PresenceMapper.toDTO(savedPresence);
    }

    public PresenceResponseDTO updatePresence(Long id, PresenceRequestDTO presenceRequestDTO) {
        Presence presence = presenceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Presence non trouvé"));
        presence.setIsPresent(presenceRequestDTO.isPresent());
        presence.setComment(presenceRequestDTO.comment());
        Lesson lesson = lessonRepository.findById(presenceRequestDTO.lessonID()).orElseThrow(() -> new ResourceNotFoundException("Leçon non trouvé"));
        presence.setLesson(lesson);
        Student student = studentRepository.findById(presenceRequestDTO.studentId()).orElseThrow(()-> new ResourceNotFoundException("Elève non trouvé"));
        presence.setStudent(student);
        presenceRepository.save(presence);
        return PresenceMapper.toDTO(presence);
    }

    public void deletePresence(Long id) {
        presenceRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Presence non trouvé"));
        presenceRepository.deleteById(id);
    }

}
