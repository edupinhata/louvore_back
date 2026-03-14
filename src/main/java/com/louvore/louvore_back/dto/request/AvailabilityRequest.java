package com.louvore.louvore_back.dto.request;

import com.louvore.louvore_back.domain.enums.AvailabilityStatus;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AvailabilityRequest(
    @NotNull UUID memberId,
    @NotNull UUID serviceEventId,
    @NotNull AvailabilityStatus status,
    String notes
) {}
