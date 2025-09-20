package org.school.controller;

import lombok.RequiredArgsConstructor;
import org.school.dto.LoginRequestDTO;
import org.school.entity.User;
import org.school.repository.UserRepository;
import org.school.service.AccountResolverService;
import org.school.service.JWTService;
import org.school.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JWTService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AccountResolverService accountResolverService;
    private final UserService userService;


    @GetMapping("/hello")
    public String message() {
        return "Hello World";
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> authenticate(@RequestBody LoginRequestDTO loginRequestDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.username(),
                        loginRequestDTO.password())
        );

        String token = jwtService.generateToken(authentication);
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public Map<String, Object> me(@AuthenticationPrincipal Jwt jwt) {
        String username = jwt.getClaimAsString("username");

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Object account = accountResolverService.resolveAccount(user);

        return Map.of(
                "token", jwt.getTokenValue(),
                "username", user.getUsername(),
                "role", user.getRole(),
                "account", account
        );
    }
}