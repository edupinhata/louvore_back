package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Availability;
import com.louvore.louvore_back.domain.entity.Member;
import com.louvore.louvore_back.domain.entity.ServiceEvent;
import com.louvore.louvore_back.dto.request.AvailabilityRequest;
import com.louvore.louvore_back.dto.response.AvailabilityResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.AvailabilityMapper;
import com.louvore.louvore_back.repository.AvailabilityRepository;
import com.louvore.louvore_back.repository.MemberRepository;
import com.louvore.louvore_back.repository.ServiceEventRepository;
import com.louvore.louvore_back.service.AvailabilityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {
    private final AvailabilityRepository availabilityRepository;
    private final MemberRepository memberRepository;
    private final ServiceEventRepository serviceEventRepository;
    private final AvailabilityMapper availabilityMapper;

    @Override
    public AvailabilityResponse create(AvailabilityRequest request) {
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", request.memberId()));
        ServiceEvent event = serviceEventRepository.findById(request.serviceEventId())
                .orElseThrow(() -> new ResourceNotFoundException("ServiceEvent", request.serviceEventId()));
        Availability availability = Availability.builder()
                .member(member).serviceEvent(event).status(request.status()).notes(request.notes()).build();
        return availabilityMapper.toResponse(availabilityRepository.save(availability));
    }

    @Override
    @Transactional(readOnly = true)
    public AvailabilityResponse findById(UUID id) {
        return availabilityRepository.findById(id)
                .map(availabilityMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Availability", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<AvailabilityResponse> findByServiceEvent(UUID serviceEventId) {
        return availabilityRepository.findByServiceEventId(serviceEventId).stream()
                .map(availabilityMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public AvailabilityResponse update(UUID id, AvailabilityRequest request) {
        Availability availability = availabilityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Availability", id));
        availability.setStatus(request.status());
        availability.setNotes(request.notes());
        return availabilityMapper.toResponse(availabilityRepository.save(availability));
    }

    @Override
    public void delete(UUID id) {
        if (!availabilityRepository.existsById(id)) throw new ResourceNotFoundException("Availability", id);
        availabilityRepository.deleteById(id);
    }
}
