package com.aluralatam.topichubapirest.domain.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DTOUpdateUser(
        @NotNull
        Long id,
        String username,
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%=*?&])[A-Za-z\\d@$!%=*?&]{8,}$")
        String password
) {
}
