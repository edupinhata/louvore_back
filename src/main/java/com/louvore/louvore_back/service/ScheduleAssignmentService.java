package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.ScheduleAssignmentRequest;
import com.louvore.louvore_back.dto.response.ScheduleAssignmentResponse;
import java.util.List;
import java.util.UUID;

public interface ScheduleAssignmentService {
    ScheduleAssignmentResponse create(ScheduleAssignmentRequest request);
    ScheduleAssignmentResponse findById(UUID id);
    List<ScheduleAssignmentResponse> findByServiceEvent(UUID serviceEventId);
    ScheduleAssignmentResponse update(UUID id, ScheduleAssignmentRequest request);
    void delete(UUID id);
}
