package org.school.service;

import org.school.dto.UserResponseDTO;
import org.school.entity.*;
import org.school.exception.ResourceNotFoundException;
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

    public UserResponseDTO resolveAccount(User user) {

        if (Account.STUDENT == (user.getAccountType())) {
            Student student = studentRepository.findById(user.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Student not found"));
            return UserResponseDTO.builder()
                    .id(student.getId())
                    .firstName(student.getLastName())
                    .lastName(student.getLastName())
                    .entity("Student")
                    .build();
        } else if (Account.TEACHER == (user.getAccountType())) {
            Teacher teacher = teacherRepository.findById(user.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Teacher not found"));
            return UserResponseDTO.builder()
                    .id(teacher.getId())
                    .firstName(teacher.getLastName())
                    .lastName(teacher.getLastName())
                    .entity("Teacher")
                    .build();
        } else if (Account.PARENT == (user.getAccountType())) {
            Parent parent = parentRepository.findById(user.getAccountId()).orElseThrow(() -> new ResourceNotFoundException("Parent not found"));
            return UserResponseDTO.builder()
                    .id(parent.getId())
                    .firstName(parent.getLastName())
                    .lastName(parent.getLastName())
                    .entity("Parent")
                    .build();
        }
        return null;
    }
}
