package com.aluralatam.topichubapirest.domain.server;

import com.aluralatam.topichubapirest.domain.server_onboarding.ServerOnboarding;
import com.aluralatam.topichubapirest.domain.topic.Topic;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "Server")
@Table(name = "servers")
public class Server {
    @Id
    @Column(name = "server_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "server")
    private Set<ServerOnboarding> serverOnboardingSet;

    @OneToMany(mappedBy = "server")
    private Set<Topic> topicSet;

    private String name;
    private String description;
    private LocalDateTime creationDatetime;
    private LocalDateTime lastUpdate;
}
