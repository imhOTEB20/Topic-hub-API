package com.aluralatam.topichubapirest.domain.message;

import com.aluralatam.topichubapirest.domain.user.DTOResponseUser;

public record DTOGetMessage(
        Long id,
        String content,
        DTOResponseUser author
) {
    public DTOGetMessage (Message message) {
        this(message.getId(), message.getContent(), new DTOResponseUser(message.getUser()));
    }
}
