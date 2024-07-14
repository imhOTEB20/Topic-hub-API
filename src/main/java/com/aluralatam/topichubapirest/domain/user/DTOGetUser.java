package com.aluralatam.topichubapirest.domain.user;

import com.aluralatam.topichubapirest.domain.server.Server;
import com.aluralatam.topichubapirest.domain.server_onboarding.DTOServerInUser;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record DTOGetUser(
        Long id,
        String username,
        String email,
        Set<DTOServerInUser> servers,
        LocalDateTime creation_datetime,
        LocalDateTime last_update
) {
    public DTOGetUser(User user) {
        this(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getServerOnboardingSet().stream()
                        .map(DTOServerInUser::new)
                        .collect(Collectors.toSet()),
                user.getCreationDatetime(),
                user.getLastUpdate()
        );
    }
}
