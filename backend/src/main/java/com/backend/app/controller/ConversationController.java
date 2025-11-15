package com.backend.app.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.dto.response.ApiResponse;
import com.backend.app.dto.response.ConversationResponse;
import com.backend.app.dto.response.PageResponse;
import com.backend.app.mapper.ConversationMapper;
import com.backend.app.model.Conversation;
import com.backend.app.security.user.AppUserDetails;
import com.backend.app.service.IConPartManagerService;
import com.backend.app.service.IConversationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/conversations")
public class ConversationController {
  
  private final IConversationService conversationService;
  private final IConPartManagerService conPartManagerService;
  private final ConversationMapper conversationMapper;


  @GetMapping("/all")
  public ResponseEntity<ApiResponse<PageResponse<ConversationResponse>>> getConversations(
    @AuthenticationPrincipal AppUserDetails user,
    @PageableDefault(page = 0, size = 20) Pageable pageable
  ) {
    Page<Conversation> conversations = conPartManagerService.getConversationsByUserId(user.getId(), pageable);
    Page<ConversationResponse> response = conversationMapper.toConversationResponsesPage(conversations);
    return ResponseEntity.ok(ApiResponse
      .success("Fetched conversations successful",
      PageResponse.fromPage(response)
    ));
  }
}
