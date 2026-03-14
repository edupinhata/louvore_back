package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Church;
import com.louvore.louvore_back.dto.request.ChurchRequest;
import com.louvore.louvore_back.dto.response.ChurchResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.ChurchMapper;
import com.louvore.louvore_back.repository.ChurchRepository;
import com.louvore.louvore_back.service.ChurchService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ChurchServiceImpl implements ChurchService {
    private final ChurchRepository churchRepository;
    private final ChurchMapper churchMapper;

    @Override
    public ChurchResponse create(ChurchRequest request) {
        Church church = churchMapper.toEntity(request);
        return churchMapper.toResponse(churchRepository.save(church));
    }

    @Override
    @Transactional(readOnly = true)
    public ChurchResponse findById(UUID id) {
        return churchRepository.findById(id)
                .map(churchMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Church", id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ChurchResponse> findAll(Pageable pageable) {
        return PageResponse.from(churchRepository.findAll(pageable).map(churchMapper::toResponse));
    }

    @Override
    public ChurchResponse update(UUID id, ChurchRequest request) {
        Church church = churchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Church", id));
        churchMapper.updateEntity(church, request);
        return churchMapper.toResponse(churchRepository.save(church));
    }

    @Override
    public void delete(UUID id) {
        if (!churchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Church", id);
        }
        churchRepository.deleteById(id);
    }
}
