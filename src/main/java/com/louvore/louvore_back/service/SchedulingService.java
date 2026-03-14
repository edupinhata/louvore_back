package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.response.ScheduleAssignmentResponse;
import java.util.List;
import java.util.UUID;

public interface SchedulingService {
    List<ScheduleAssignmentResponse> generateSchedule(UUID serviceEventId);
}
