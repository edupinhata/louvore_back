package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.ServiceEvent;
import com.louvore.louvore_back.dto.response.ServiceEventResponse;
import org.springframework.stereotype.Component;

@Component
public class ServiceEventMapper {
    public ServiceEventResponse toResponse(ServiceEvent event) {
        return new ServiceEventResponse(
                event.getId(),
                event.getChurch().getId(), event.getChurch().getName(),
                event.getMinistry() != null ? event.getMinistry().getId() : null,
                event.getMinistry() != null ? event.getMinistry().getName() : null,
                event.getTitle(), event.getDateTime(), event.getType(),
                event.getTheme(), event.getNotes(), event.getStatus(),
                event.getCreatedAt(), event.getUpdatedAt());
    }
}
