package com.aluralatam.topichubapirest.domain.message;

import com.aluralatam.topichubapirest.domain.old_message.OldMessage;
import com.aluralatam.topichubapirest.domain.topic.Topic;
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
@Entity(name = "Message")
@Table(name = "messages")
public class Message {

    @Id
    @Column(name = "message_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(mappedBy = "message")
    private Set<OldMessage> oldMessageSet;

    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    private String content;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
}
