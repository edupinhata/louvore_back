package com.louvore.louvore_back.dto.request;

import jakarta.validation.constraints.NotBlank;

public record InstrumentRequest(
    @NotBlank String name,
    String category
) {}
