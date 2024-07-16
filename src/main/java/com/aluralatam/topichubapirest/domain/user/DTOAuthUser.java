package com.aluralatam.topichubapirest.domain.user;

import jakarta.validation.constraints.NotBlank;

public record DTOAuthUser(
        @NotBlank
        String username,
        @NotBlank
        String password
) {
}
