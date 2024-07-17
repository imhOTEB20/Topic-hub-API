package com.aluralatam.topichubapirest.domain.topic;

import jakarta.validation.constraints.NotBlank;

public record DTOCreateTopic(
        @NotBlank
        Long server_id,
        @NotBlank
        Long creator_user_id,
        @NotBlank
        String name,
        @NotBlank
        String description
) {
}
