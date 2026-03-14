package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.Availability;
import com.louvore.louvore_back.dto.response.AvailabilityResponse;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityMapper {
    public AvailabilityResponse toResponse(Availability availability) {
        return new AvailabilityResponse(
                availability.getId(),
                availability.getMember().getId(), availability.getMember().getFullName(),
                availability.getServiceEvent().getId(), availability.getServiceEvent().getTitle(),
                availability.getStatus(), availability.getNotes(),
                availability.getCreatedAt(), availability.getUpdatedAt());
    }
}
