package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Church;
import com.louvore.louvore_back.domain.entity.Ministry;
import com.louvore.louvore_back.domain.entity.ServiceEvent;
import com.louvore.louvore_back.domain.enums.ServiceEventStatus;
import com.louvore.louvore_back.dto.request.ServiceEventRequest;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.dto.response.ServiceEventResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.ServiceEventMapper;
import com.louvore.louvore_back.repository.ChurchRepository;
import com.louvore.louvore_back.repository.MinistryRepository;
import com.louvore.louvore_back.repository.ServiceEventRepository;
import com.louvore.louvore_back.service.ServiceEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceEventServiceImpl implements ServiceEventService {
    private final ServiceEventRepository serviceEventRepository;
    private final ChurchRepository churchRepository;
    private final MinistryRepository ministryRepository;
    private final ServiceEventMapper serviceEventMapper;

    @Override
    public ServiceEventResponse create(ServiceEventRequest request) {
        Church church = churchRepository.findById(request.churchId())
                .orElseThrow(() -> new ResourceNotFoundException("Church", request.churchId()));
        Ministry ministry = null;
        if (request.ministryId() != null) {
            ministry = ministryRepository.findById(request.ministryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ministry", request.ministryId()));
        }
        ServiceEvent event = ServiceEvent.builder()
                .church(church).ministry(ministry).title(request.title())
                .dateTime(request.dateTime()).type(request.type()).theme(request.theme())
                .notes(request.notes())
                .status(request.status() != null ? request.status() : ServiceEventStatus.PLANNED)
                .build();
        return serviceEventMapper.toResponse(serviceEventRepository.save(event));
    }

    @Override
    @Transactional(readOnly = true)
    public ServiceEventResponse findById(UUID id) {
        return serviceEventRepository.findById(id)
                .map(serviceEventMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceEvent", id));
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<ServiceEventResponse> findByChurch(UUID churchId, Pageable pageable) {
        return PageResponse.from(serviceEventRepository.findByChurchId(churchId, pageable)
                .map(serviceEventMapper::toResponse));
    }

    @Override
    public ServiceEventResponse update(UUID id, ServiceEventRequest request) {
        ServiceEvent event = serviceEventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ServiceEvent", id));
        Church church = churchRepository.findById(request.churchId())
                .orElseThrow(() -> new ResourceNotFoundException("Church", request.churchId()));
        Ministry ministry = null;
        if (request.ministryId() != null) {
            ministry = ministryRepository.findById(request.ministryId())
                    .orElseThrow(() -> new ResourceNotFoundException("Ministry", request.ministryId()));
        }
        event.setChurch(church);
        event.setMinistry(ministry);
        event.setTitle(request.title());
        event.setDateTime(request.dateTime());
        event.setType(request.type());
        event.setTheme(request.theme());
        event.setNotes(request.notes());
        if (request.status() != null) event.setStatus(request.status());
        return serviceEventMapper.toResponse(serviceEventRepository.save(event));
    }

    @Override
    public void delete(UUID id) {
        if (!serviceEventRepository.existsById(id)) throw new ResourceNotFoundException("ServiceEvent", id);
        serviceEventRepository.deleteById(id);
    }
}
