package org.school.service;

import lombok.RequiredArgsConstructor;
import org.school.dto.PaginationResponseDTO;
import org.school.dto.UserResponseDTO;
import org.school.entity.User;
import org.school.exception.ResourceNotFoundException;
import org.school.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountResolverService accountResolverService;

    public void createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public PaginationResponseDTO<UserResponseDTO> getAllUsers(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("username").descending());

        Page<User> usersPage = userRepository.findAll(pageable);
        Page<UserResponseDTO> users = usersPage.map(accountResolverService::resolveAccount);
        return new PaginationResponseDTO<>(users);
    }

    public void deleteUser(Integer userId) {
        userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("Utilisateur introuvable"));
        userRepository.deleteById(userId);
    }
}
