package org.school.repository;

import org.school.entity.Parent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends JpaRepository<Parent, Long> {
    Page<Parent> findByFirstNameContainingIgnoreCaseOrderByFirstNameAsc(String name, Pageable pageable);
}
