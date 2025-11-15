package com.backend.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.app.model.Participant;

public interface IParticipantService {

  List<Participant> getParticipantsByConversationId(String conversationId);

  boolean isParticipant(String conversationId, Long userId);

  Page<Participant> getParticipantsByUserId(Long userId, Pageable pageable);

  Participant saveParticipant(Participant participant);

  void removeParticipant(String participantId);

  Participant getParticipantByConversationIdAndUserId(String conversationId, Long userId);
}
