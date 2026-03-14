package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.ServiceEventRequest;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.dto.response.ScheduleAssignmentResponse;
import com.louvore.louvore_back.dto.response.ServiceEventResponse;
import com.louvore.louvore_back.service.SchedulingService;
import com.louvore.louvore_back.service.ServiceEventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/service-events")
@RequiredArgsConstructor
public class ServiceEventController {
    private final ServiceEventService serviceEventService;
    private final SchedulingService schedulingService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<ServiceEventResponse> create(@Valid @RequestBody ServiceEventRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(serviceEventService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEventResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(serviceEventService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ServiceEventResponse>> findByChurch(
            @RequestParam UUID churchId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(serviceEventService.findByChurch(churchId, pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<ServiceEventResponse> update(@PathVariable UUID id, @Valid @RequestBody ServiceEventRequest request) {
        return ResponseEntity.ok(serviceEventService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        serviceEventService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/generate-schedule")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<List<ScheduleAssignmentResponse>> generateSchedule(@PathVariable UUID id) {
        return ResponseEntity.ok(schedulingService.generateSchedule(id));
    }
}
