package com.louvore.louvore_back.dto.response;

import java.time.Instant;
import java.util.UUID;

public record SongResponse(
    UUID id,
    String title,
    String artist,
    String originalKey,
    Integer bpm,
    String timeSignature,
    String themeTags,
    boolean active,
    Instant createdAt,
    Instant updatedAt
) {}
