package com.backend.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.backend.app.exception.BadRequestException;
import com.backend.app.model.Friend;
import com.backend.app.repository.FriendRepository;
import com.backend.app.service.IFriendService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FriendService implements IFriendService {
  
  private final FriendRepository friendRepository;

  @Override
  public Page<Friend> getFriends(Long userId, Pageable pageable) {
    return friendRepository.findByUserId(userId, pageable);
  }

  @Override
  public void unFriend(Long userId, Long friendId) {
    if (areFriends(userId, friendId)) {
      friendRepository.deleteByUserIdAndFriendId(userId, friendId);
      friendRepository.deleteByUserIdAndFriendId(friendId, userId);
    } else {
      throw new BadRequestException("Not friends");
    }
  }

  @Override
  public boolean areFriends(Long userId1, Long userId2) {
    return friendRepository.existsByUserIdAndFriendId(userId1, userId2);
  }

  @Override
  public void saveFriend(Friend friend) {
    friendRepository.save(friend);
  }
}
