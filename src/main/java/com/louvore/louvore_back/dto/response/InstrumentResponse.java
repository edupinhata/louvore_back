package com.louvore.louvore_back.dto.response;

import java.time.Instant;
import java.util.UUID;

public record InstrumentResponse(
    UUID id,
    String name,
    String category,
    Instant createdAt,
    Instant updatedAt
) {}
