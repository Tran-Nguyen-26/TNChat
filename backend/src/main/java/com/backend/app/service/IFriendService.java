package com.backend.app.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.backend.app.model.Friend;

public interface IFriendService {
  Page<Friend> getFriends(Long userId, Pageable pageable);
  void unFriend(Long userId, Long friendId);
  boolean areFriends(Long userId1, Long userId2);
  void saveFriend(Friend friend);
}
