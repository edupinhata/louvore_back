package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.Church;
import com.louvore.louvore_back.dto.request.ChurchRequest;
import com.louvore.louvore_back.dto.response.ChurchResponse;
import org.springframework.stereotype.Component;

@Component
public class ChurchMapper {
    public Church toEntity(ChurchRequest request) {
        return Church.builder()
                .name(request.name())
                .city(request.city())
                .state(request.state())
                .active(request.active() != null ? request.active() : true)
                .build();
    }
    public ChurchResponse toResponse(Church church) {
        return new ChurchResponse(
                church.getId(), church.getName(), church.getCity(), church.getState(),
                church.isActive(), church.getCreatedAt(), church.getUpdatedAt());
    }
    public void updateEntity(Church church, ChurchRequest request) {
        church.setName(request.name());
        church.setCity(request.city());
        church.setState(request.state());
        if (request.active() != null) church.setActive(request.active());
    }
}
