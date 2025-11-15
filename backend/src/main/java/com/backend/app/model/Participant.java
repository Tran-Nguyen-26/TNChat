package com.backend.app.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class Participant {
  @Id
  private String id;
  private Long userId;
  private String conversationId;
  private String lastSeenMessageId;
  private LocalDateTime lastSeenAt;
  private LocalDateTime joinAt;
  private Long addedBy;
}
