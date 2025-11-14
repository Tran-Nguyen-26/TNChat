package com.backend.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.dto.response.ApiResponse;
import com.backend.app.dto.response.FriendResponse;
import com.backend.app.dto.response.PageResponse;
import com.backend.app.mapper.FriendMapper;
import com.backend.app.model.Friend;
import com.backend.app.security.user.AppUserDetails;
import com.backend.app.service.IFriendService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/friends")
public class FriendController {
  
  private final IFriendService friendService;
  private final FriendMapper friendMapper;

  @GetMapping("/alls")
  public ResponseEntity<ApiResponse<PageResponse<FriendResponse>>> getFriends(
      @AuthenticationPrincipal AppUserDetails user,
      @PageableDefault(page = 0, size = 20) Pageable pageable) {
    Page<Friend> friends = friendService.getFriends(user.getId(), pageable);
    Page<FriendResponse> response = friendMapper.toFriendResponsePage(friends);
    return ResponseEntity.ok(ApiResponse
      .success("Fetched friends successfully", 
      PageResponse.fromPage(response)
    ));
  }

  @DeleteMapping("/un-friends/{friendId}")
  public ResponseEntity<ApiResponse<?>> unFriend(
      @AuthenticationPrincipal AppUserDetails user, 
      @PathVariable Long friendId) {
    friendService.unFriend(user.getId(), friendId);
    return ResponseEntity.ok(ApiResponse.success("Unfriend successful"));
  }
}
