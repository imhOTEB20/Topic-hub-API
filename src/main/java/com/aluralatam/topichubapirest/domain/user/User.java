package com.aluralatam.topichubapirest.domain.user;

import com.aluralatam.topichubapirest.domain.message.Message;
import com.aluralatam.topichubapirest.domain.server_onboarding.ServerOnboarding;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "User")
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "user")
    private Set<ServerOnboarding> serverOnboardingSet;

    @OneToMany(mappedBy = "user")
    private Set<Message> messageSet;

    private String userName;
    private String password;
    private String email;
    private LocalDateTime creationDatetime;
    private LocalDateTime lastUpdate;
}
