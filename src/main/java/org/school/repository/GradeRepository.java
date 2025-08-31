package org.school.repository;

import org.school.entity.Grade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends JpaRepository<Grade, Long> {
    Page<Grade> findByNameContainingIgnoreCaseOrderByNameAsc(String name, Pageable pageable);
}
