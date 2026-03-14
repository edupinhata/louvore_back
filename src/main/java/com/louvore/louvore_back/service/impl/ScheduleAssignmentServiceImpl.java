package com.louvore.louvore_back.service.impl;

import com.louvore.louvore_back.domain.entity.Member;
import com.louvore.louvore_back.domain.entity.ScheduleAssignment;
import com.louvore.louvore_back.domain.entity.ServiceEvent;
import com.louvore.louvore_back.domain.enums.AssignmentStatus;
import com.louvore.louvore_back.dto.request.ScheduleAssignmentRequest;
import com.louvore.louvore_back.dto.response.ScheduleAssignmentResponse;
import com.louvore.louvore_back.exception.ResourceNotFoundException;
import com.louvore.louvore_back.mapper.ScheduleAssignmentMapper;
import com.louvore.louvore_back.repository.MemberRepository;
import com.louvore.louvore_back.repository.ScheduleAssignmentRepository;
import com.louvore.louvore_back.repository.ServiceEventRepository;
import com.louvore.louvore_back.service.ScheduleAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ScheduleAssignmentServiceImpl implements ScheduleAssignmentService {
    private final ScheduleAssignmentRepository scheduleAssignmentRepository;
    private final MemberRepository memberRepository;
    private final ServiceEventRepository serviceEventRepository;
    private final ScheduleAssignmentMapper scheduleAssignmentMapper;

    @Override
    public ScheduleAssignmentResponse create(ScheduleAssignmentRequest request) {
        ServiceEvent event = serviceEventRepository.findById(request.serviceEventId())
                .orElseThrow(() -> new ResourceNotFoundException("ServiceEvent", request.serviceEventId()));
        Member member = memberRepository.findById(request.memberId())
                .orElseThrow(() -> new ResourceNotFoundException("Member", request.memberId()));
        ScheduleAssignment assignment = ScheduleAssignment.builder()
                .serviceEvent(event).member(member).role(request.role())
                .status(request.status() != null ? request.status() : AssignmentStatus.SUGGESTED)
                .generatedAutomatically(request.generatedAutomatically() != null ? request.generatedAutomatically() : false)
                .build();
        return scheduleAssignmentMapper.toResponse(scheduleAssignmentRepository.save(assignment));
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleAssignmentResponse findById(UUID id) {
        return scheduleAssignmentRepository.findById(id)
                .map(scheduleAssignmentMapper::toResponse)
                .orElseThrow(() -> new ResourceNotFoundException("ScheduleAssignment", id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleAssignmentResponse> findByServiceEvent(UUID serviceEventId) {
        return scheduleAssignmentRepository.findByServiceEventId(serviceEventId).stream()
                .map(scheduleAssignmentMapper::toResponse).collect(Collectors.toList());
    }

    @Override
    public ScheduleAssignmentResponse update(UUID id, ScheduleAssignmentRequest request) {
        ScheduleAssignment assignment = scheduleAssignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ScheduleAssignment", id));
        assignment.setRole(request.role());
        if (request.status() != null) assignment.setStatus(request.status());
        if (request.generatedAutomatically() != null) assignment.setGeneratedAutomatically(request.generatedAutomatically());
        return scheduleAssignmentMapper.toResponse(scheduleAssignmentRepository.save(assignment));
    }

    @Override
    public void delete(UUID id) {
        if (!scheduleAssignmentRepository.existsById(id)) throw new ResourceNotFoundException("ScheduleAssignment", id);
        scheduleAssignmentRepository.deleteById(id);
    }
}
