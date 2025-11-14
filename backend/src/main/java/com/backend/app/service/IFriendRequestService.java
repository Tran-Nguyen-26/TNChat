package com.backend.app.service;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.backend.app.model.FriendRequest;

public interface IFriendRequestService {
  FriendRequest getRequestById(Long id);
  FriendRequest sendFriendRequest(Long senderId, Long receiverId);
  void acceptFriendRequest(Long requestId, Long currentUserId);
  void rejectFriendRequest(Long requestId, Long currentUserId);
  void cancelFriendRequest(Long requestId, Long currentUserId);
  Page<FriendRequest> getReceivedRequests(Long userId, Pageable pageable);
  Page<FriendRequest> getSendRequests(Long userId, Pageable pageable);
}
