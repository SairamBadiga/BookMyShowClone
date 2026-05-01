package com.badiga.catelogservices.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@Entity
@Getter @Setter
@Table(name = "outbox_event")
public class OutboxEvent {

    @Id
    @GeneratedValue
    private Long id;

    private String aggregateType;   // SHOW
    private Long aggregateId;       // showId

    private String type;            // SHOW_CREATED

    @Lob
    private String payload;

    @Enumerated(EnumType.STRING)
    private Status status;          // NEW, PROCESSING, SENT, FAILED

    private int retryCount;

    private LocalDateTime createdAt;
}
