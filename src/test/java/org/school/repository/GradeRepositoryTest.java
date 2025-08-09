package org.school.repository;

import org.junit.jupiter.api.Test;
import org.school.entity.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class GradeRepositoryTest {

    @Autowired
    GradeRepository gradeRepository;

    @Test
    void shouldGetAllGrades() {
        List<Grade> grades = gradeRepository.findAll();
        assertEquals(5, grades.size());
        assertEquals("CP", grades.getFirst().getName());
    }


    @Test
    void shouldGetGradeById() {
        Grade grade = gradeRepository.findById(1L).get();
        assertEquals("CP", grade.getName());
    }

    @Test
    void shouldSavePerson() {
        Grade grade = new Grade();
        grade.setName("EP");
        gradeRepository.save(grade);
        assertNotNull(grade.getId());
    }

    @Test
    void shouldDeleteGradeById() {
        gradeRepository.deleteById(1L);
        Optional<Grade> grade = gradeRepository.findById(1L);

        assertTrue(grade.isEmpty());
    }

    @Test
    void shouldUpdateGrade() {
        Grade grade = new Grade();
        grade.setName("MOD");
        grade = gradeRepository.save(grade);
        assertEquals("MOD", grade.getName());
    }
}