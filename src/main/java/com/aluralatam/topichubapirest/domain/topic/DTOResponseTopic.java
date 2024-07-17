package com.aluralatam.topichubapirest.domain.topic;

import java.time.LocalDateTime;

public record DTOResponseTopic(
        Long id,
        String name,
        String description,
        LocalDateTime creation_datetime
) {
    public DTOResponseTopic(Topic topic) {
        this(
                topic.getCreatorUser().getId(),
                topic.getName(),
                topic.getDescription(),
                topic.getCreationDatetime());
    }
}
