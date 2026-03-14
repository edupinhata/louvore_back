package com.louvore.louvore_back.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ChurchRequest(
    @NotBlank String name,
    String city,
    String state,
    Boolean active
) {}
