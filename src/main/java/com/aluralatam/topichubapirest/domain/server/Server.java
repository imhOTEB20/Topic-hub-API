package com.aluralatam.topichubapirest.domain.server;

import com.aluralatam.topichubapirest.domain.server_onboarding.ServerOnboarding;
import com.aluralatam.topichubapirest.domain.topic.Topic;
import com.aluralatam.topichubapirest.domain.user.DTOUpdateUser;
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

    @Column(name = "creation_datetime")
    private LocalDateTime creationDatetime;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    private String name;
    private String description;

    public Server(DTORegisterServer dtoRegisterServer) {
        this.name = dtoRegisterServer.name();
        this.description = dtoRegisterServer.description();
    }

    public void updateData(DTOUpdateServer dtoUpdateServer) {
        if(dtoUpdateServer.name() != null && !dtoUpdateServer.name().isEmpty())
            this.name = dtoUpdateServer.name();
        if(dtoUpdateServer.description() != null && !dtoUpdateServer.description().isEmpty())
            this.description = dtoUpdateServer.description();
    }
}
