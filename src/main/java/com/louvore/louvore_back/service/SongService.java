package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.SongRequest;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.dto.response.SongResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface SongService {
    SongResponse create(SongRequest request);
    SongResponse findById(UUID id);
    PageResponse<SongResponse> findAll(Pageable pageable);
    PageResponse<SongResponse> search(String title, Pageable pageable);
    SongResponse update(UUID id, SongRequest request);
    void delete(UUID id);
}
