package com.backend.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.app.model.Friend;

public interface FriendRepository extends JpaRepository<Friend, Long> {

  Page<Friend> findByUserId(Long userId, Pageable pageable);

  boolean existsByUserIdAndFriendId(Long userId1, Long userId2);

  void deleteByUserIdAndFriendId(Long userId, Long friendId);
  
}
