package com.louvore.louvore_back.dto.response;

import com.louvore.louvore_back.domain.enums.ServiceEventStatus;
import com.louvore.louvore_back.domain.enums.ServiceEventType;
import java.time.Instant;
import java.util.UUID;

public record ServiceEventResponse(
    UUID id,
    UUID churchId,
    String churchName,
    UUID ministryId,
    String ministryName,
    String title,
    Instant dateTime,
    ServiceEventType type,
    String theme,
    String notes,
    ServiceEventStatus status,
    Instant createdAt,
    Instant updatedAt
) {}
