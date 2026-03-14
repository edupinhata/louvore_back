package com.louvore.louvore_back.dto.response;

import java.time.Instant;
import java.util.UUID;

public record ChurchResponse(
    UUID id,
    String name,
    String city,
    String state,
    boolean active,
    Instant createdAt,
    Instant updatedAt
) {}
