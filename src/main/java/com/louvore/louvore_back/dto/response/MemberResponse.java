package com.louvore.louvore_back.dto.response;

import java.time.Instant;
import java.util.UUID;

public record MemberResponse(
    UUID id,
    String fullName,
    String email,
    String phone,
    boolean active,
    UUID churchId,
    String churchName,
    Instant createdAt,
    Instant updatedAt
) {}
