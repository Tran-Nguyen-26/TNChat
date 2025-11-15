package com.backend.app.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend.app.dto.response.ConversationResponse;
import com.backend.app.model.Conversation;

@Component
public class ConversationMapper {

  public ConversationResponse toConversationResponse(Conversation conversation) {
    return ConversationResponse.builder()
      .id(conversation.getId())
      .name(conversation.getName())
      .type(conversation.getType())
      .lastMessageId(conversation.getLastMessageId())
      .createdAt(conversation.getCreatedAt())
      .build();
  }

  public Page<ConversationResponse> toConversationResponsesPage(Page<Conversation> conversations) {
    return conversations.map(this::toConversationResponse);
  }
  
}
