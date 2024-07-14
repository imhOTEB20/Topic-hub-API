package com.aluralatam.topichubapirest.domain.server_onboarding;

import java.time.LocalDateTime;

public record DTOServerInUser(
        Long id,
        String name,
        String description,
        LocalDateTime joined_to_server
) {
    public DTOServerInUser(ServerOnboarding serverOnboarding) {
        this(
                serverOnboarding.getServer().getId(),
                serverOnboarding.getServer().getName(),
                serverOnboarding.getServer().getDescription(),
                serverOnboarding.getJoinedDatetime()
        );
    }
}
