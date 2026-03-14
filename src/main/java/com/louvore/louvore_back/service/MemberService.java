package com.louvore.louvore_back.service;

import com.louvore.louvore_back.dto.request.MemberRequest;
import com.louvore.louvore_back.dto.response.MemberResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface MemberService {
    MemberResponse create(MemberRequest request);
    MemberResponse findById(UUID id);
    PageResponse<MemberResponse> findByChurch(UUID churchId, Pageable pageable);
    MemberResponse update(UUID id, MemberRequest request);
    void delete(UUID id);
}
