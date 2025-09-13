package org.school;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.school.dto.PresenceRequestDTO;
import org.school.mapper.PresenceMapper;
import org.school.repository.*;
import org.school.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
public class SchoolApplication {

    private final LessonRepository lessonRepository;
    private final LessonService lessonService;
    private final ClasseRepository classRepository;
    private final GradeRepository gradeRepository;
    private final ParentRepository parentRepository;
    private final ParentService parentService;
    private final PresenceRepository presenceRepository;
    private final PresenceService presenceService;
    StudentRepository studentRepository;
    TeacherRepository teacherRepository;
    SubjectRepository subjectRepository;
    TeacherService teacherService;
    SubjectService subjectService;
    ClasseService classeService;
    StudentService studentService;

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }

}
