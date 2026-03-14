package com.louvore.louvore_back.dto.response;

public record LoginResponse(
    String token,
    String username,
    String role
) {}
