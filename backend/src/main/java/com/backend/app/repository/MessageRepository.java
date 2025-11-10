package com.backend.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.app.model.Message;

public interface MessageRepository extends MongoRepository<Message, String> {

}
