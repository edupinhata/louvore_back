package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.ScheduleAssignmentRequest;
import com.louvore.louvore_back.dto.response.ScheduleAssignmentResponse;
import com.louvore.louvore_back.service.ScheduleAssignmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/schedule-assignments")
@RequiredArgsConstructor
public class ScheduleAssignmentController {
    private final ScheduleAssignmentService scheduleAssignmentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<ScheduleAssignmentResponse> create(@Valid @RequestBody ScheduleAssignmentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(scheduleAssignmentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleAssignmentResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(scheduleAssignmentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ScheduleAssignmentResponse>> findByServiceEvent(@RequestParam UUID serviceEventId) {
        return ResponseEntity.ok(scheduleAssignmentService.findByServiceEvent(serviceEventId));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<ScheduleAssignmentResponse> update(@PathVariable UUID id, @Valid @RequestBody ScheduleAssignmentRequest request) {
        return ResponseEntity.ok(scheduleAssignmentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        scheduleAssignmentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
