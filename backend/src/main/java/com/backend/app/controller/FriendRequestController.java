package com.backend.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.dto.response.ApiResponse;
import com.backend.app.dto.response.FriendRequestResponse;
import com.backend.app.dto.response.PageResponse;
import com.backend.app.mapper.FriendRequestMapper;
import com.backend.app.model.FriendRequest;
import com.backend.app.security.user.AppUserDetails;
import com.backend.app.service.IFriendRequestService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/friends/requests")
public class FriendRequestController {
  
  private final IFriendRequestService requestService;
  private final FriendRequestMapper friendRequestMapper;

  @PostMapping("/{receiverId}")
  public ResponseEntity<ApiResponse<?>> sendFriendRequest(
      @AuthenticationPrincipal AppUserDetails user,
      @PathVariable Long receiverId) {
    requestService.sendFriendRequest(user.getId(), receiverId);
    return ResponseEntity.ok(ApiResponse.success("Friend request sent"));
  }

  @PatchMapping("/{requestId}/accept")
  public ResponseEntity<ApiResponse<?>> acceptFriendRequest(
      @AuthenticationPrincipal AppUserDetails user,
      @PathVariable Long requestId
  ) {
    requestService.acceptFriendRequest(requestId, user.getId());
    return ResponseEntity.ok(ApiResponse.success("Friend request accepted"));
  }

  @PatchMapping("/{requestId}/reject")
  public ResponseEntity<ApiResponse<?>> rejectFriendRequest(
    @AuthenticationPrincipal AppUserDetails user,
    @PathVariable Long requestId
  ) {
    requestService.rejectFriendRequest(requestId, user.getId());
    return ResponseEntity.ok(ApiResponse.success("Friend request rejected"));
  }

  @DeleteMapping("/{requestId}/cancel")
  public ResponseEntity<ApiResponse<?>> cancelFriendRequest(
    @AuthenticationPrincipal AppUserDetails user,
    @PathVariable Long requestId
  ) {
    requestService.cancelFriendRequest(requestId, user.getId());
    return ResponseEntity.ok(ApiResponse.success("Friend request cancelled"));
  }

  @GetMapping("/received")
  public ResponseEntity<ApiResponse<PageResponse<FriendRequestResponse>>> getReceivedRequests(
    @AuthenticationPrincipal AppUserDetails user,
    @PageableDefault(page = 0, size = 20) Pageable pageable
  ) {
    Page<FriendRequest> request = requestService.getReceivedRequests(user.getId(), pageable);
    Page<FriendRequestResponse> response = friendRequestMapper.toFriendRequestReponsePage(request);
    return ResponseEntity.ok(ApiResponse
      .success("Fetched received requests successfully",
      PageResponse.fromPage(response)
    ));
  }

  @GetMapping("/send")
  public ResponseEntity<ApiResponse<PageResponse<FriendRequestResponse>>> getSendRequests(
    @AuthenticationPrincipal AppUserDetails user,
    @PageableDefault(page = 0, size = 20) Pageable pageable
  ) {
    Page<FriendRequest> request = requestService.getSendRequests(user.getId(), pageable);
    Page<FriendRequestResponse> response = friendRequestMapper.toFriendRequestReponsePage(request);
    return ResponseEntity.ok(ApiResponse
      .success("Fetched send requests successfully",
      PageResponse.fromPage(response)
    ));
  }
}
