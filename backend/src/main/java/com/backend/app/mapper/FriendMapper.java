package com.backend.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend.app.dto.response.FriendResponse;
import com.backend.app.model.Friend;
import com.backend.app.model.User;

import io.jsonwebtoken.lang.Collections;

@Component
public class FriendMapper {
  public FriendResponse toFriendResponse(Friend friend) {
    User friendUser = friend.getFriend();
    FriendResponse response = FriendResponse.builder()
      .id(friendUser.getId())
      .username(friendUser.getUsername())
      .createdAt(friend.getCreatedAt())
      .build();
    return response;
  }

  public List<FriendResponse> toFriendResponseList(List<Friend> friends) {
    if (friends == null) return Collections.emptyList();
    return friends.stream().map(this::toFriendResponse)
    .collect(Collectors.toList());
  }

  public Page<FriendResponse> toFriendResponsePage(Page<Friend> friends) {
    return friends.map(this::toFriendResponse);
  }
}
