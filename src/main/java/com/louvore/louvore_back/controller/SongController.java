package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.SongRequest;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.dto.response.SongResponse;
import com.louvore.louvore_back.service.SongService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/songs")
@RequiredArgsConstructor
public class SongController {
    private final SongService songService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<SongResponse> create(@Valid @RequestBody SongRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(songService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SongResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(songService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<SongResponse>> findAll(
            @RequestParam(required = false) String title,
            @PageableDefault(size = 20) Pageable pageable) {
        if (title != null && !title.isBlank()) {
            return ResponseEntity.ok(songService.search(title, pageable));
        }
        return ResponseEntity.ok(songService.findAll(pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<SongResponse> update(@PathVariable UUID id, @Valid @RequestBody SongRequest request) {
        return ResponseEntity.ok(songService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        songService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
