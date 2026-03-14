package com.louvore.louvore_back.dto.response;

import com.louvore.louvore_back.domain.enums.AssignmentRole;
import com.louvore.louvore_back.domain.enums.AssignmentStatus;
import java.time.Instant;
import java.util.UUID;

public record ScheduleAssignmentResponse(
    UUID id,
    UUID serviceEventId,
    String serviceEventTitle,
    UUID memberId,
    String memberName,
    AssignmentRole role,
    AssignmentStatus status,
    boolean generatedAutomatically,
    Instant createdAt,
    Instant updatedAt
) {}
