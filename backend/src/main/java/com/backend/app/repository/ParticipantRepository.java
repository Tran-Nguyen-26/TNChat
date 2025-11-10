package com.backend.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.backend.app.model.Participant;

public interface ParticipantRepository extends MongoRepository<Participant, String> {

}
