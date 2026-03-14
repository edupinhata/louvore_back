package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.ChurchRequest;
import com.louvore.louvore_back.dto.response.ChurchResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.service.ChurchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/churches")
@RequiredArgsConstructor
public class ChurchController {
    private final ChurchService churchService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChurchResponse> create(@Valid @RequestBody ChurchRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(churchService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChurchResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(churchService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<ChurchResponse>> findAll(@PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(churchService.findAll(pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ChurchResponse> update(@PathVariable UUID id, @Valid @RequestBody ChurchRequest request) {
        return ResponseEntity.ok(churchService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        churchService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
