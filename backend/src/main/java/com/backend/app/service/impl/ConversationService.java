package com.backend.app.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.app.dto.request.ConversationUpdateRequest;
import com.backend.app.enums.ConversationType;
import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.Conversation;
import com.backend.app.model.Participant;
import com.backend.app.repository.ConversationRepository;
import com.backend.app.service.IConversationService;
import com.backend.app.service.IParticipantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ConversationService implements IConversationService {
  
  private final ConversationRepository conversationRepository;
  private final IParticipantService participantService;

  @Override
  public Conversation getConversationById(String conversationId) {
    return conversationRepository.findById(conversationId)
      .orElseThrow(() -> new ResourceNotFoundException("Conversation not found with id: " + conversationId));
  }

  @Override
  public Conversation updateConversation(String conversationId, ConversationUpdateRequest request) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'updateConversation'");
  }

  @Override
  public void deleteConversation(String conversationId, Long userId) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deleteConversation'");
  }


  @Override
  public Conversation findDirectConversation(Long userId1, Long userId2) {
    Page<Participant> user1Participants = participantService.getParticipantsByUserId(userId1, Pageable.unpaged());

    for (Participant participant : user1Participants) {
      String conversationId = participant.getConversationId();
      Conversation conversation = getConversationById(conversationId);
      if (conversation.getType() != ConversationType.DIRECT) {
        continue;
      }
      List<Participant> participants = participantService.getParticipantsByConversationId(conversationId);
      if (participants.size() != 2) {
        continue;
      }
      boolean hasUser2 = participants.stream()
        .anyMatch(p -> p.getUserId().equals(userId2));
      if (hasUser2) {
        return conversation;
      }
    }
    return null;
  }

	@Override
	public Conversation saveConversation(Conversation conversation) {
		return conversationRepository.save(conversation);
	}
}
