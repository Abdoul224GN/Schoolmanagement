package org.school.service;

import org.school.entity.User;
import org.school.repository.ParentRepository;
import org.school.repository.StudentRepository;
import org.school.repository.TeacherRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountResolverService {
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final ParentRepository parentRepository;

    public AccountResolverService(StudentRepository studentRepository,
                                  TeacherRepository teacherRepository, ParentRepository parentRepository) {
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
        this.parentRepository = parentRepository;
    }

    public Object resolveAccount(User user) {
        if ("Student".equalsIgnoreCase(user.getAccountType())) {
            return studentRepository.findById(user.getAccountId()).orElse(null);
        } else if ("Teacher".equalsIgnoreCase(user.getAccountType())) {
            return teacherRepository.findById(user.getAccountId()).orElse(null);
        } else if ("Parent".equalsIgnoreCase(user.getAccountType())) {
            return parentRepository.findById(user.getAccountId()).orElse(null);
        }
        return null;
    }
}
