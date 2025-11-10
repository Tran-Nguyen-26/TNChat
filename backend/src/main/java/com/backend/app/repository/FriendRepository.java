package com.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.app.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {
  
}
