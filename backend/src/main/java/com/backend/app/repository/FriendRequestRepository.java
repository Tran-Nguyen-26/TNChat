package com.backend.app.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.app.enums.StatusRequest;
import com.backend.app.model.FriendRequest;

public interface FriendRequestRepository extends JpaRepository<FriendRequest, Long> {

  Optional<FriendRequest> findBySenderIdAndReceiverIdAndStatus(Long senderId, Long receiverId, StatusRequest pending);

  Optional<FriendRequest> findBySenderIdAndReceiverId(Long senderId, Long receiverId);

  Page<FriendRequest> findByReceiverIdAndStatus(Long userId, StatusRequest pending, Pageable pageable);

  Page<FriendRequest> findBySenderIdAndStatus(Long userId, StatusRequest pending, Pageable pageable);
  
}
