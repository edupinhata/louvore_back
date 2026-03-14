package com.louvore.louvore_back.controller;

import com.louvore.louvore_back.dto.request.MemberRequest;
import com.louvore.louvore_back.dto.response.MemberResponse;
import com.louvore.louvore_back.dto.response.PageResponse;
import com.louvore.louvore_back.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody MemberRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberResponse> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(memberService.findById(id));
    }

    @GetMapping
    public ResponseEntity<PageResponse<MemberResponse>> findByChurch(
            @RequestParam UUID churchId,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(memberService.findByChurch(churchId, pageable));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'LEADER')")
    public ResponseEntity<MemberResponse> update(@PathVariable UUID id, @Valid @RequestBody MemberRequest request) {
        return ResponseEntity.ok(memberService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        memberService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
