package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.ChurchRequest;
import com.louvore.louvore_back.dto.response.ChurchResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ChurchService {
    ChurchResponse create(ChurchRequest request);
    ChurchResponse findById(UUID id);
    PageResponse<ChurchResponse> findAll(Pageable pageable);
    ChurchResponse update(UUID id, ChurchRequest request);
    void delete(UUID id);
}
