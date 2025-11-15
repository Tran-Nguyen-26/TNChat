package com.backend.app.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.app.model.Participant;

public interface ParticipantRepository extends MongoRepository<Participant, String> {

  List<Participant> findByConversationId(String conversationId);

  boolean existsByConversationIdAndUserId(String conversationId, Long userId);

  Page<Participant> findByUserId(Long userId, Pageable pageable);

  Optional<Participant> findByConversationIdAndUserId(String conversationId, Long userId);

}
