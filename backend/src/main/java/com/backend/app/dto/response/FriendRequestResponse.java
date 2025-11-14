package com.backend.app.dto.response;

import java.time.LocalDateTime;

import com.backend.app.enums.StatusRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FriendRequestResponse {
  private Long requestId;
  private Long senderId;
  private Long receiverId;
  private StatusRequest status;
  private LocalDateTime createdAt;
}
