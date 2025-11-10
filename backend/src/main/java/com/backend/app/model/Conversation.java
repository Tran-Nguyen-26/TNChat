package com.backend.app.model;

import java.time.LocalDateTime;

import org.springframework.data.mongodb.core.mapping.Document;

import com.backend.app.enums.ConversationType;

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
@Builder
@Document
public class Conversation {
  @Id
  private String id;
  private String name;
  private ConversationType type;
  private String lastMessageId;
  private Long createdByUserId;
  private LocalDateTime createdAt;
}
