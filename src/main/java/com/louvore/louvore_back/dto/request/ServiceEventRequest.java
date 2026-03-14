package com.louvore.louvore_back.dto.request;

import com.louvore.louvore_back.domain.enums.ServiceEventStatus;
import com.louvore.louvore_back.domain.enums.ServiceEventType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

public record ServiceEventRequest(
    @NotNull UUID churchId,
    UUID ministryId,
    @NotBlank String title,
    @NotNull Instant dateTime,
    @NotNull ServiceEventType type,
    String theme,
    String notes,
    ServiceEventStatus status
) {}
