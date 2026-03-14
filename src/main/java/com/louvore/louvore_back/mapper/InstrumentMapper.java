package com.louvore.louvore_back.mapper;

import com.louvore.louvore_back.domain.entity.Instrument;
import com.louvore.louvore_back.dto.request.InstrumentRequest;
import com.louvore.louvore_back.dto.response.InstrumentResponse;
import org.springframework.stereotype.Component;

@Component
public class InstrumentMapper {
    public Instrument toEntity(InstrumentRequest request) {
        return Instrument.builder().name(request.name()).category(request.category()).build();
    }
    public InstrumentResponse toResponse(Instrument instrument) {
        return new InstrumentResponse(instrument.getId(), instrument.getName(), instrument.getCategory(),
                instrument.getCreatedAt(), instrument.getUpdatedAt());
    }
    public void updateEntity(Instrument instrument, InstrumentRequest request) {
        instrument.setName(request.name());
        instrument.setCategory(request.category());
    }
}
