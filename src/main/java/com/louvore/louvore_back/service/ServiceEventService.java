package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.ServiceEventRequest;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.dto.response.ServiceEventResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ServiceEventService {
    ServiceEventResponse create(ServiceEventRequest request);
    ServiceEventResponse findById(UUID id);
    PageResponse<ServiceEventResponse> findByChurch(UUID churchId, Pageable pageable);
    ServiceEventResponse update(UUID id, ServiceEventRequest request);
    void delete(UUID id);
}
