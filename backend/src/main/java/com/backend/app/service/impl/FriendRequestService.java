package com.backend.app.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.backend.app.enums.StatusRequest;
import com.backend.app.exception.BadRequestException;
import com.backend.app.exception.ResourceNotFoundException;
import com.backend.app.model.Friend;
import com.backend.app.model.FriendRequest;
import com.backend.app.model.User;
import com.backend.app.repository.FriendRequestRepository;
import com.backend.app.service.IFriendRequestService;
import com.backend.app.service.IFriendService;
import com.backend.app.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendRequestService implements IFriendRequestService {

  private final FriendRequestRepository friendRequestRepository;
  private final IFriendService friendService;
  private final IUserService userService;

  @Override
  public FriendRequest getRequestById(Long requestId) {
    return friendRequestRepository.findById(requestId)
      .orElseThrow(() -> new ResourceNotFoundException("Friend request not found with id: " + requestId));
  }

  @Override
  public FriendRequest sendFriendRequest(Long senderId, Long receiverId) {
    if (senderId.equals(receiverId)) {
      throw new BadRequestException("Cannot send friend request to your self");
    }

    User sender = userService.getUserById(senderId);
    User receiver = userService.getUserById(receiverId);

    if (friendService.areFriends(senderId, receiverId)) {
      throw new BadRequestException("Already friends");
    }

    Optional<FriendRequest> existingRequest = friendRequestRepository
      .findBySenderIdAndReceiverId(senderId, receiverId);

    if (existingRequest.isPresent()) {
      throw new BadRequestException("Already friend request");
    }

    FriendRequest request = FriendRequest.builder()
        .sender(sender)
        .receiver(receiver)
        .status(StatusRequest.PENDING)
        .createdAt(LocalDateTime.now())
        .build();
    
    return friendRequestRepository.save(request);
  }

  @Override
  public void acceptFriendRequest(Long requestId, Long currentUserId) {
    FriendRequest request = getRequestById(requestId);
    
    if (!request.getReceiver().getId().equals(currentUserId)) {
      throw new BadRequestException("You can only accept requests sent to you");
    }

    if (request.getStatus() != StatusRequest.PENDING) {
      throw new BadRequestException("Request already proccessed");
    }

    request.setStatus(StatusRequest.ACCEPT);
    friendRequestRepository.save(request);

    Friend friend1 = Friend.builder()
      .user(request.getSender())
      .friend(request.getReceiver())
      .createdAt(LocalDateTime.now())
      .build();

    Friend friend2 = Friend.builder()
      .user(request.getReceiver())
      .friend(request.getSender())
      .createdAt(LocalDateTime.now())
      .build();
    
    friendService.saveFriend(friend1);
    friendService.saveFriend(friend2);
  }

  @Override
  public void rejectFriendRequest(Long requestId, Long currentUserId) {
    FriendRequest request = getRequestById(requestId);

    if (!request.getReceiver().getId().equals(currentUserId)) {
      throw new BadRequestException("You can only reject requests sent to you");
    }

    if (request.getStatus() != StatusRequest.PENDING) {
      throw new BadRequestException("Request already proccessed");
    }

    request.setStatus(StatusRequest.REJECT);
    friendRequestRepository.save(request);
  }

  @Override
  public void cancelFriendRequest(Long requestId, Long currentUserId) {
    FriendRequest request = getRequestById(requestId);

    if (!request.getSender().getId().equals(currentUserId)) {
      throw new BadRequestException("You can only cancel your own requests");
    }

    if (request.getStatus() != StatusRequest.PENDING) {
      throw new BadRequestException("Can only cancel pending requets");
    }

    friendRequestRepository.delete(request);
  }

  @Override
  public Page<FriendRequest> getReceivedRequests(Long userId, Pageable pageable) {
    return friendRequestRepository.findByReceiverIdAndStatus(
      userId,
      StatusRequest.PENDING,
      pageable
    );
  }

  @Override
  public Page<FriendRequest> getSendRequests(Long userId, Pageable pageable) {
    return friendRequestRepository.findBySenderIdAndStatus(
      userId,
      StatusRequest.PENDING,
      pageable
    );
  }
}
