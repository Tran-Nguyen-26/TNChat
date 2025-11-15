package com.backend.app.service;

import com.backend.app.dto.request.ConversationUpdateRequest;
import com.backend.app.model.Conversation;

public interface IConversationService {

  public Conversation getConversationById(String conversationId);

  public Conversation updateConversation(String conversationId, ConversationUpdateRequest request);

  public void deleteConversation(String conversationId, Long userId);

  public Conversation findDirectConversation(Long userId1, Long userId2);
  
  public Conversation saveConversation(Conversation conversation);
}
