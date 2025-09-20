package org.school.repository;

import org.school.entity.ParentEleve;
import org.school.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ParentEleveRepository extends JpaRepository<ParentEleve, Long> {
    @Query("SELECT pe.student FROM ParentEleve pe WHERE pe.parent.id = :parentId")
    Page<Student> findStudentsByParentId(Long parentId, Pageable pageable);
}
