package com.aluralatam.topichubapirest.domain.old_message;

import com.aluralatam.topichubapirest.domain.message.Message;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "OldMessage")
@Table(name = "old_messages")
public class OldMessage {
    @Id
    @Column(name = "old_messages_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "message_id")
    private Message message;

    private String content;
}
