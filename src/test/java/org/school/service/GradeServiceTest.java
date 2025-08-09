package org.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.school.dto.GradeRequestDTO;
import org.school.dto.GradeResponseDTO;
import org.school.entity.Grade;
import org.school.mapper.GradeMapper;
import org.school.repository.GradeRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GradeServiceTest {

    @Mock
    private GradeRepository repository;

    @InjectMocks
    private GradeService service;

    @Test
    void shouldReturnAllGrades() {
        Grade grade1 = new Grade();
        grade1.setId(1L);
        grade1.setName("test");
        Grade grade2 = new Grade();
        grade2.setId(1L);
        grade2.setName("test");
        List<Grade> list = List.of(grade1, grade2);
        when(repository.findAll()).thenReturn(list);
        List<GradeResponseDTO> result = service.getAllGrade();
        assertThat(result).hasSize(2).containsExactly(GradeMapper.toDTO(grade1), GradeMapper.toDTO(grade2));
    }

    @Test
    void shouldReturnGradeByID() {
        Grade grade1 = new Grade();
        grade1.setId(1L);
        grade1.setName("test");
        when(repository.findById(1L)).thenReturn(Optional.of(grade1));
        assertThat(service.getGradeById(1L)).isEqualTo(GradeMapper.toDTO(grade1));
    }

    @Test
    void shouldReturnGradeOnSave() {
        GradeRequestDTO requestDTO = new GradeRequestDTO("test");

        Grade savedEntity = new Grade();
        savedEntity.setId(2L);
        savedEntity.setName("test");

        when(repository.save(any(Grade.class))).thenReturn(savedEntity);
        assertThat(service.createGrade(requestDTO).id()).isEqualTo(savedEntity.getId());
    }

    @Test
   void shouldDeleteById() {
        Long id = 1L;
        Grade grade = new Grade();
        grade.setId(id);

        // Mock le findById pour qu'il renvoie l'entité
        when(repository.findById(id)).thenReturn(Optional.of(grade));
        service.deleteGrade(id);
        verify(repository, times(1)).deleteById(id);
   }
}