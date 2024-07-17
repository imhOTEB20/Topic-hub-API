package com.aluralatam.topichubapirest.infra.validation;

import com.aluralatam.topichubapirest.domain.server.Server;
import com.aluralatam.topichubapirest.domain.server.ServerRepository;
import com.aluralatam.topichubapirest.domain.server_onboarding.DTOUserInServer;
import com.aluralatam.topichubapirest.domain.user.User;
import com.aluralatam.topichubapirest.domain.user.UserRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInServer {
    @Autowired
    ServerRepository serverRepository;

    @Autowired
    UserRepository userRepository;

    public boolean validate(Server server, User creatorUser) {
        if (server.getServerOnboardingSet().stream()
                .map(DTOUserInServer::new)
                .anyMatch(user -> creatorUser.getId().equals(user.id()))
        ) {
            return true;
        } else {
            throw new ValidationException("The user does not belong to the server.");
        }
    }
}
