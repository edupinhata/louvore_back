package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Instrument;
import com.louvore.louvore_back.dto.request.InstrumentRequest;
import com.louvore.louvore_back.dto.response.InstrumentResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.InstrumentMapper;
import com.louvore.louvore_back.repository.InstrumentRepository;
import com.louvore.louvore_back.service.InstrumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class InstrumentServiceImpl implements InstrumentService {
    private final InstrumentRepository instrumentRepository;
    private final InstrumentMapper instrumentMapper;

    @Override
    public InstrumentResponse create(InstrumentRequest request) {
        return instrumentMapper.toResponse(instrumentRepository.save(instrumentMapper.toEntity(request)));
    }

    @Override
    @Transactional(readOnly = true)
    public InstrumentResponse findById(UUID id) {
        return instrumentRepository.findById(id)
                .map(instrumentMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Instrument", id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<InstrumentResponse> findAll(Pageable pageable) {
        return PageResponse.from(instrumentRepository.findAll(pageable).map(instrumentMapper::toResponse));
    }

    @Override
    public InstrumentResponse update(UUID id, InstrumentRequest request) {
        Instrument instrument = instrumentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Instrument", id));
        instrumentMapper.updateEntity(instrument, request);
        return instrumentMapper.toResponse(instrumentRepository.save(instrument));
    }

    @Override
    public void delete(UUID id) {
        if (!instrumentRepository.existsById(id)) throw new ResourceNotFoundException("Instrument", id);
        instrumentRepository.deleteById(id);
    }
}
