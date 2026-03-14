package com.louvore.louvore_back.dto.request;

import com.louvore.louvore_back.domain.enums.AssignmentRole;
import com.louvore.louvore_back.domain.enums.AssignmentStatus;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ScheduleAssignmentRequest(
    @NotNull UUID serviceEventId,
    @NotNull UUID memberId,
    @NotNull AssignmentRole role,
    AssignmentStatus status,
    Boolean generatedAutomatically
) {}
