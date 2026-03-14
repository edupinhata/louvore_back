package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.dto.request.LoginRequest;
import com.louvore.louvore_back.dto.response.LoginResponse;
import com.louvore.louvore_back.repository.UserRepository;
import com.louvore.louvore_back.security.JwtService;
import com.louvore.louvore_back.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));
        String role = userRepository.findByUsernameAndActiveTrue(request.username())
                .map(u -> u.getRole().name()).orElse("MEMBER");
        String token = jwtService.generateToken(request.username(), role);
        return new LoginResponse(token, request.username(), role);
    }
}
