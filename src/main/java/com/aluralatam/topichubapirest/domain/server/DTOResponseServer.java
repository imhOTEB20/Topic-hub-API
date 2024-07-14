package com.aluralatam.topichubapirest.domain.server;

import com.aluralatam.topichubapirest.domain.user.DTOResponseUser;

import java.time.LocalDateTime;

public record DTOResponseServer(
        Long id,
        String name,
        String description,
        LocalDateTime creation_datetime
) {
    public DTOResponseServer(Server server) {
        this(server.getId(), server.getName(), server.getDescription(), server.getCreationDatetime());
    }
}
