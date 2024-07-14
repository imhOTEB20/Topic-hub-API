package com.aluralatam.topichubapirest.domain.user;

public record DTOResponseUser(
        Long id,
        String username,
        String email
        //Set<Server> serverSet,
        //Set<Topic> topicSet
) {
    public DTOResponseUser(User user) {
        this(user.getId(), user.getUsername(), user.getEmail());
    }
}
