package com.aluralatam.topichubapirest.domain.server;

import jakarta.validation.constraints.NotBlank;

public record DTORegisterServer(
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
