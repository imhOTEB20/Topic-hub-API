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

    @Column(name = "creation_datetime")
    private LocalDateTime creationDatetime;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    private String username;
    private String password;
    private String email;

    public User(DTORegisterUser dtoRegisterUser) {
        this.username = dtoRegisterUser.username();
        this.email = dtoRegisterUser.email();
        this.password = dtoRegisterUser.password();
    }

    public void updateData(DTOUpdateUser dtoUpdateUser) {
        if(dtoUpdateUser.username() != null && !dtoUpdateUser.username().isEmpty())
            this.setUsername(dtoUpdateUser.username());
        if(dtoUpdateUser.email() != null && !dtoUpdateUser.email().isEmpty())
            this.setEmail(dtoUpdateUser.email());
        if(dtoUpdateUser.password() != null && !dtoUpdateUser.password().isEmpty())
            this.setPassword(dtoUpdateUser.password());
    }
}
