package com.aluralatam.topichubapirest.domain.server;

import jakarta.validation.constraints.NotNull;

public record DTOUpdateServer(
        @NotNull
        Long id,
        String name,
        String description
) {
}
