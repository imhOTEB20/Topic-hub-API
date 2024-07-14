package com.aluralatam.topichubapirest.domain.server_onboarding;

import java.time.LocalDateTime;

public record DTOUserInServer(
        Long id,
        String name,
        String email,
        LocalDateTime joined_to_server
) {
    public DTOUserInServer(ServerOnboarding serverOnboarding) {
        this(
                serverOnboarding.getUser().getId(),
                serverOnboarding.getUser().getUsername(),
                serverOnboarding.getUser().getEmail(),
                serverOnboarding.getJoinedDatetime()
        );
    }
}
