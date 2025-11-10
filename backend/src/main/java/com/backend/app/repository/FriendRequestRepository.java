package com.backend.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.app.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {
  
}
