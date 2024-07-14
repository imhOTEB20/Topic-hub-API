package com.aluralatam.topichubapirest.domain.server;

import com.aluralatam.topichubapirest.domain.topic.DTOResponseTopic;
import com.aluralatam.topichubapirest.domain.server_onboarding.DTOUserInServer;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record DTOGetServer(
        Long id,
        String name,
        String description,
        Set<DTOResponseTopic> topics,
        Set<DTOUserInServer> users,
        LocalDateTime last_update,
        LocalDateTime creation_datetime
) {
    public DTOGetServer(Server server) {
        this(
                server.getId(),
                server.getName(),
                server.getDescription(),
                server.getTopicSet().stream()
                        .map(DTOResponseTopic::new)
                        .collect(Collectors.toSet()),
                server.getServerOnboardingSet().stream()
                        .map(DTOUserInServer::new)
                        .collect(Collectors.toSet()),
                server.getLastUpdate(),
                server.getCreationDatetime()
        );
    }
}
