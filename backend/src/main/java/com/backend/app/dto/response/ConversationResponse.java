package com.backend.app.dto.response;

import java.time.LocalDateTime;

import com.backend.app.enums.ConversationType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConversationResponse {
  private String id;
  private String name;
  private ConversationType type;
  private String lastMessageId;
  private LocalDateTime createdAt;
}
