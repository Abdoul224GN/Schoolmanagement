package org.school.service;

import lombok.AllArgsConstructor;
import org.school.entity.Gender;
import org.school.repository.ClasseRepository;
import org.school.repository.StudentRepository;
import org.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DashboardService {
    private final TeacherRepository teacherRepository;
    private final ClasseRepository classeRepository;
    private StudentRepository studentRepository;

    public long countStudent() {
        return studentRepository.count();
    }

    public long countTeacher() {
        return teacherRepository.count();
    }

    public long countStudentByGender(Gender gender) {
        return studentRepository.countBySex(gender);
    }

    public long countClasses() {
        return classeRepository.count();
    }
}
