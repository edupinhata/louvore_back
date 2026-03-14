package com.louvore.louvore_back.dto.response;

import com.louvore.louvore_back.domain.enums.AvailabilityStatus;
import java.time.Instant;
import java.util.UUID;

public record AvailabilityResponse(
    UUID id,
    UUID memberId,
    String memberName,
    UUID serviceEventId,
    String serviceEventTitle,
    AvailabilityStatus status,
    String notes,
    Instant createdAt,
    Instant updatedAt
) {}
