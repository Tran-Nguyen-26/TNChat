package com.backend.app.mapper;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import com.backend.app.dto.response.FriendRequestResponse;
import com.backend.app.model.FriendRequest;
import com.backend.app.model.User;

@Component
public class FriendRequestMapper {
  public FriendRequestResponse toFriendRequestReponse(FriendRequest friendRequest) {
    User sender = friendRequest.getSender();
    User receiver = friendRequest.getReceiver();
    FriendRequestResponse response = FriendRequestResponse.builder()
      .requestId(friendRequest.getId())
      .senderId(sender.getId())
      .receiverId(receiver.getId())
      .status(friendRequest.getStatus())
      .createdAt(friendRequest.getCreatedAt())
      .build();
    return response;
  }

  public Page<FriendRequestResponse> toFriendRequestReponsePage(Page<FriendRequest> requests) {
    return requests.map(this::toFriendRequestReponse);
  }
}
