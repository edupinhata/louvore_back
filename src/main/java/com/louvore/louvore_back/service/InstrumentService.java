package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.InstrumentRequest;
import com.louvore.louvore_back.dto.response.InstrumentResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface InstrumentService {
    InstrumentResponse create(InstrumentRequest request);
    InstrumentResponse findById(UUID id);
    PageResponse<InstrumentResponse> findAll(Pageable pageable);
    InstrumentResponse update(UUID id, InstrumentRequest request);
    void delete(UUID id);
}
