package org.school.repository;

import org.school.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Integer> {
    List<Schedule> findByClasseId(Long classeId);

    List<Schedule> findByTeacherId(Long teacherId);

}
