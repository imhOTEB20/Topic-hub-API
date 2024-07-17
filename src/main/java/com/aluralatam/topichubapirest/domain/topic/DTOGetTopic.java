package com.aluralatam.topichubapirest.domain.topic;

import com.aluralatam.topichubapirest.domain.message.DTOGetMessage;
import com.aluralatam.topichubapirest.domain.user.DTOResponseUser;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record DTOGetTopic(
        Long id,
        String name,
        String description,
        DTOResponseUser creator_user,
        Set<DTOGetMessage> menssages,
        LocalDateTime creation_datetime
) {
    public DTOGetTopic (Topic topic) {
        this(
                topic.getId(),
                topic.getName(),
                topic.getDescription(),
                new DTOResponseUser(topic.getCreatorUser()),
                topic.getMessageSet().stream()
                        .map(DTOGetMessage::new)
                        .collect(Collectors.toSet()),
                topic.getCreationDatetime()
        );
    }
}
