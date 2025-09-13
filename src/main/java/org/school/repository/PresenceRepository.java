package org.school.repository;

import org.school.entity.Presence;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {
    Page<Presence> findPresenceByLesson_Id(Long coursId, Pageable pageable);
}
