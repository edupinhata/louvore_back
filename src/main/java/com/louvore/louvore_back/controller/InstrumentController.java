package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.InstrumentRequest;
import com.louvore.louvore_back.dto.response.InstrumentResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.service.InstrumentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/instruments")
@RequiredArgsConstructor
public class InstrumentController {
    private final InstrumentService instrumentService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<InstrumentResponse> create(@Valid @RequestBody InstrumentRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(instrumentService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<InstrumentResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(instrumentService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<InstrumentResponse>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(instrumentService.findAll(pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<InstrumentResponse> update(@PathVariable UUID id, @Valid @RequestBody InstrumentRequest request) {
        return ResponseEntity.ok(instrumentService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        instrumentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
