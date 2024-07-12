package com.aluralatam.topichubapirest.domain.server_onboarding;

import com.aluralatam.topichubapirest.domain.server.Server;
import com.aluralatam.topichubapirest.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "ServerOnboarding")
@Table(name = "server_onboarding")
public class ServerOnboarding {
    @Id
    @Column(name = "server_onboarding_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;

    private LocalDateTime joinedDatetime;
}
