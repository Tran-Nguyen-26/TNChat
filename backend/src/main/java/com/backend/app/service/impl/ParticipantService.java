package com.backend.app.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.Participant;
import com.backend.app.repository.ParticipantRepository;
import com.backend.app.service.IParticipantService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipantService implements IParticipantService {
  
  private final ParticipantRepository participantRepository;

  @Override
  public List<Participant> getParticipantsByConversationId(String conversationId) {
    return participantRepository.findByConversationId(conversationId);
  }

  @Override
  public boolean isParticipant(String conversationId, Long userId) {
    return participantRepository.existsByConversationIdAndUserId(conversationId, userId);
  }

  @Override
  public Page<Participant> getParticipantsByUserId(Long userId, Pageable pageable) {
    return participantRepository.findByUserId(userId, pageable);
  }

  @Override
  public Participant saveParticipant(Participant participant) {
    return participantRepository.save(participant);
  }

  @Override
  public void removeParticipant(String participantId) {
    participantRepository.deleteById(participantId);
  }

  @Override
  public Participant getParticipantByConversationIdAndUserId(String conversationId, Long userId) {
    return participantRepository.findByConversationIdAndUserId(conversationId, userId)
      .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
  }

  
}
