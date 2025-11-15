package com.backend.app.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.app.dto.request.ConversationCreateRequest;
import com.backend.app.enums.ConversationType;
import com.backend.app.exception.BadRequestException;
import com.backend.app.model.Conversation;
import com.backend.app.model.Participant;
import com.backend.app.service.IConPartManagerService;
import com.backend.app.service.IConversationService;
import com.backend.app.service.IParticipantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConPartManagerService implements IConPartManagerService {
  
  private final IConversationService conversationService;
  private final IParticipantService participantService;

  @Override
  public void addParticipant(String conversationId, Long userId, Long addedBy) {
    if (userId.equals(addedBy)) {
      throw new BadRequestException("Cannot add mysefl");
    }
    
    Conversation conversation = conversationService.getConversationById(conversationId);

    if (conversation.getType().equals(ConversationType.DIRECT)) {
      throw new BadRequestException("Cannot add user to a DIRECT conversation");
    }

    if (!addedBy.equals(conversation.getCreatedByUserId())) {
      throw new BadRequestException("Only the creator of th conversation can add users");
    }

    if (participantService.isParticipant(conversationId, userId)) {
      throw new BadRequestException("User is already in the conversation");
    }

    Participant participant = Participant.builder()
      .userId(userId)
      .conversationId(conversationId)
      .joinAt(LocalDateTime.now())
      .addedBy(addedBy)
      .build();
    
    participantService.saveParticipant(participant);
  }

  @Override
  public void removeParticipant(String conversationId, Long userId, Long removedBy) {
    if (userId.equals(removedBy)) {
      throw new BadRequestException("Cannot remove mysefl");
    }

    Conversation conversation = conversationService.getConversationById(conversationId);

    if (!removedBy.equals(conversation.getCreatedByUserId())) {
      throw new BadRequestException("Only the creator of th conversation can remove users");
    }

    if (!participantService.isParticipant(conversationId, userId)) {
      throw new BadRequestException("User is't already in the conversation");
    }

    Participant participant = participantService.getParticipantByConversationIdAndUserId(conversationId, userId);

    participantService.removeParticipant(participant.getId());

  }

  @Override
  public void leaveParticipant(String coversationId, Long userId) {
    Participant participant = participantService.getParticipantByConversationIdAndUserId(coversationId, userId);
    participantService.removeParticipant(participant.getId());
  }

  @Override
  public Conversation createDirectConversation(Long creatorId, Long friendId) {
    if (creatorId.equals(friendId)) {
      throw new BadRequestException("Cannot create conversation with yourself");
    }

    Conversation existing = conversationService.findDirectConversation(creatorId, friendId);
    if (existing != null) {
      return existing;
    }

    Conversation conversation = Conversation.builder()
      .name(null)
      .type(ConversationType.DIRECT)
      .createdAt(LocalDateTime.now())
      .build();

    conversation = conversationService.saveConversation(conversation);
    Participant participant1 = Participant.builder()
      .userId(creatorId)
      .conversationId(conversation.getId())
      .build();
    Participant participant2 = Participant.builder()
      .userId(friendId)
      .conversationId(conversation.getId())
      .build();
    participantService.saveParticipant(participant1);
    participantService.saveParticipant(participant2);
    return conversation;
  }

  @Override
  public Conversation createGroupConversation(ConversationCreateRequest request, Long creatorId, List<Long> memberIds) {
    Conversation conversation = Conversation.builder()
      .name(request.getName())
      .type(ConversationType.GROUP)
      .createdByUserId(creatorId)
      .createdAt(LocalDateTime.now())
      .build();

    conversation = conversationService.saveConversation(conversation);

    Participant participant = Participant.builder()
      .userId(creatorId)
      .conversationId(conversation.getId())
      .joinAt(LocalDateTime.now())
      .build();

    participantService.saveParticipant(participant);

    for (Long memberId : memberIds) {
      Participant p = Participant.builder()
        .userId(memberId)
        .conversationId(conversation.getId())
        .joinAt(LocalDateTime.now())
        .addedBy(creatorId)
        .build();
      
      participantService.saveParticipant(p);
    }

    return conversation;
  }

  @Override
  public Page<Conversation> getConversationsByUserId(Long userId, Pageable pageable) {
    Page<Participant> participants = participantService.getParticipantsByUserId(userId, pageable);
    List<Conversation> conversations = new ArrayList<>();
    for (Participant participant : participants.getContent()) {
      Conversation conversation = conversationService.getConversationById(participant.getConversationId());
      conversations.add(conversation);
    }
    return new PageImpl<>(conversations, pageable, participants.getTotalElements());
  }
}
