package com.louvore.louvore_back.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SongRequest(
    @NotBlank String title,
    String artist,
    String originalKey,
    Integer bpm,
    String timeSignature,
    String themeTags,
    Boolean active
) {}
