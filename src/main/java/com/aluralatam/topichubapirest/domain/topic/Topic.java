package com.aluralatam.topichubapirest.domain.topic;

import com.aluralatam.topichubapirest.domain.message.Message;
import com.aluralatam.topichubapirest.domain.server.Server;
import com.aluralatam.topichubapirest.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Topic")
@Table(name = "topics")
public class Topic {
    @Id
    @Column(name = "topic_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    @ManyToOne
    @JoinColumn(name = "creator_user")
    private User creatorUser;

    @OneToMany(mappedBy = "topic")
    private Set<Message> messageSet;

    @Column(name = "creation_datetime")
    private LocalDateTime creationDatetime;

    private String name;
    private String description;

    public Topic(DTOCreateTopic dtoCreateTopic, Server server, User creatorUser) {
        this.server = server;
        this.creatorUser = creatorUser;
        this.name = dtoCreateTopic.name();
        this.description = dtoCreateTopic.description();
        this.creationDatetime = LocalDateTime.now();
    }
}
