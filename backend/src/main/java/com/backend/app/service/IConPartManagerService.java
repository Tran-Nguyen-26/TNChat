package com.backend.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.app.dto.request.ConversationCreateRequest;
import com.backend.app.model.Conversation;

public interface IConPartManagerService {

  public void addParticipant(String conversationId, Long userId, Long addedBy);

  public void removeParticipant(String conversationId, Long userId, Long removedBy);

  public void leaveParticipant(String coversationId, Long userId);

  public Conversation createDirectConversation(Long creatorId, Long friendId);

  public Conversation createGroupConversation(ConversationCreateRequest request, Long creatorId, List<Long> memberIds);

  public Page<Conversation> getConversationsByUserId(Long userId, Pageable pageable);
}
