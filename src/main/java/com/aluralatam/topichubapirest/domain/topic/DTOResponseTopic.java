package com.aluralatam.topichubapirest.domain.topic;

import java.time.LocalDateTime;

public record DTOResponseTopic(
        String name,
        String description,
        LocalDateTime creation_datetime
) {
    public DTOResponseTopic(Topic topic) {
        this(topic.getName(), topic.getDescription(), topic.getCreationDatetime());
    }
}
