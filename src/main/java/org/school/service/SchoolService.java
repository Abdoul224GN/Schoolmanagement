package org.school.service;

import org.school.entity.School;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.SchoolRepository;
import org.springframework.stereotype.Service;

@Service
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School getSchool() {
        return schoolRepository.findById(1).orElseThrow(() -> new ResourceNotFoundException("School Not Found"));
    }

    public School updateSchool(School school) {
        return schoolRepository.save(school);
    }
}
