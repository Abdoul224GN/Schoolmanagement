package org.school.repository;

import org.school.entity.Lesson;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    Page<Lesson> findByClasse_id(Long classeID, Pageable pageable);

    Page<Lesson> findByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);

    @Query("SELECT l FROM Lesson l " +
            "LEFT JOIN FETCH l.presences p " +
            "WHERE l.day >= :startDate")
    List<Lesson> findLessonsWithPresenceLast7Days(@Param("startDate") LocalDate startDate);
}
