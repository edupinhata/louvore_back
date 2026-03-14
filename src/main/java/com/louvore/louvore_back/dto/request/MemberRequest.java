package com.louvore.louvore_back.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record MemberRequest(
    @NotBlank String fullName,
    @Email String email,
    String phone,
    Boolean active,
    @NotNull UUID churchId
) {}
