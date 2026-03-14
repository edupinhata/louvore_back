package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.AvailabilityRequest;
import com.louvore.louvore_back.dto.response.AvailabilityResponse;
import java.util.List;
import java.util.UUID;

public interface AvailabilityService {
    AvailabilityResponse create(AvailabilityRequest request);
    AvailabilityResponse findById(UUID id);
    List<AvailabilityResponse> findByServiceEvent(UUID serviceEventId);
    AvailabilityResponse update(UUID id, AvailabilityRequest request);
    void delete(UUID id);
}
