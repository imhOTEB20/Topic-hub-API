package com.aluralatam.topichubapirest.controller;

import com.aluralatam.topichubapirest.domain.server.ServerRepository;
import com.aluralatam.topichubapirest.domain.server_onboarding.DTOUserInServer;
import com.aluralatam.topichubapirest.domain.topic.DTOCreateTopic;
import com.aluralatam.topichubapirest.domain.topic.DTOResponseTopic;
import com.aluralatam.topichubapirest.domain.topic.Topic;
import com.aluralatam.topichubapirest.domain.topic.TopicRepository;
import com.aluralatam.topichubapirest.domain.user.UserRepository;
import com.aluralatam.topichubapirest.infra.validation.UserInServer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topic")
public class TopicController {
    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private UserInServer userInServer;

    @PostMapping
    public ResponseEntity createTopic(@RequestBody @Valid DTOCreateTopic dtoCreateTopic,
                                                        UriComponentsBuilder uriComponentsBuilder) {
        var server = serverRepository.getReferenceById(dtoCreateTopic.server_id());
        var creator_user = userRepository.getReferenceById(dtoCreateTopic.creator_user_id());
        if (userInServer.validate(server, creator_user)) {
            var topic = topicRepository.save(new Topic(dtoCreateTopic, server, creator_user));
            URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
            return ResponseEntity.created(url).body(new DTOResponseTopic(topic));
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("The user does not belong to the server.");
        }
    }
}
