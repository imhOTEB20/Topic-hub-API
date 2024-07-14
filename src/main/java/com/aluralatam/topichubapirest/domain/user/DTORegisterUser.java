package com.aluralatam.topichubapirest.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DTORegisterUser(
        @NotBlank
        String username,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%=*?&])[A-Za-z\\d@$!%=*?&]{8,}$")
        String password
) {
}
