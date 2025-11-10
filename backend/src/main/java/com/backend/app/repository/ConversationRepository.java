package com.backend.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.app.model.Conversation;

public interface ConversationRepository extends MongoRepository<Conversation, String> {
  
}
