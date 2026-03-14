package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.AvailabilityRequest;
import com.louvore.louvore_back.dto.response.AvailabilityResponse;
import com.louvore.louvore_back.service.AvailabilityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/availabilities")
@RequiredArgsConstructor
public class AvailabilityController {
    private final AvailabilityService availabilityService;

    @PostMapping
    public ResponseEntity<AvailabilityResponse> create(@Valid @RequestBody AvailabilityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(availabilityService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(availabilityService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<AvailabilityResponse>> findByServiceEvent(@RequestParam UUID serviceEventId) {
        return ResponseEntity.ok(availabilityService.findByServiceEvent(serviceEventId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AvailabilityResponse> update(@PathVariable UUID id, @Valid @RequestBody AvailabilityRequest request) {
        return ResponseEntity.ok(availabilityService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        availabilityService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
