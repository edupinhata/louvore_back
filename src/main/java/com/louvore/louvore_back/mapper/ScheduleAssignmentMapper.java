package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.ScheduleAssignment;
import com.louvore.louvore_back.dto.response.ScheduleAssignmentResponse;
import org.springframework.stereotype.Component;

@Component
public class ScheduleAssignmentMapper {
    public ScheduleAssignmentResponse toResponse(ScheduleAssignment assignment) {
        return new ScheduleAssignmentResponse(
                assignment.getId(),
                assignment.getServiceEvent().getId(), assignment.getServiceEvent().getTitle(),
                assignment.getMember().getId(), assignment.getMember().getFullName(),
                assignment.getRole(), assignment.getStatus(), assignment.isGeneratedAutomatically(),
                assignment.getCreatedAt(), assignment.getUpdatedAt());
    }
}
