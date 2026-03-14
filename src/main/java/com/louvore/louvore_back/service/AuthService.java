package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.LoginRequest;
import com.louvore.louvore_back.dto.response.LoginResponse;

public interface AuthService {
    LoginResponse login(LoginRequest request);
}
