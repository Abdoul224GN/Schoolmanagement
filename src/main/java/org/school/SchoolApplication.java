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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableJpaAuditing
@AllArgsConstructor
public class SchoolApplication implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("admin123"));
    }

    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
    }


}
