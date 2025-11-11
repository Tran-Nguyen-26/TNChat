package com.backend.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.app.dto.request.UserLoginRequest;
import com.backend.app.dto.request.UserRegisterRequest;
import com.backend.app.dto.response.JwtResponse;
import com.backend.app.response.ApiResponse;
import com.backend.app.security.jwt.JwtUtils;
import com.backend.app.security.user.AppUserDetails;
import com.backend.app.service.IUserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("${api.prefix}/auth")
@RequiredArgsConstructor
public class AuthController {
  
  private final AuthenticationManager authenticationManager;
  private final JwtUtils jwtUtils;
  private final IUserService userService;

  @PostMapping("/signup")
  public ResponseEntity<ApiResponse<JwtResponse>> register(@RequestBody @Valid UserRegisterRequest request) {
    userService.register(request);
    Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateTokenForUser(authentication);
    AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
    JwtResponse response = JwtResponse.builder()
        .token(jwt)
        .id(userDetails.getId())
        .username(userDetails.getUsername())
        .build();
    return ResponseEntity.ok(ApiResponse.success("Registration successful", response));
  }

  @PostMapping("/login")
  public ResponseEntity<ApiResponse<JwtResponse>> login(@RequestBody @Valid UserLoginRequest request) {
    Authentication authentication = authenticationManager
      .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateTokenForUser(authentication);
    AppUserDetails userDetails = (AppUserDetails) authentication.getPrincipal();
    JwtResponse response = JwtResponse.builder()
        .token(jwt)
        .id(userDetails.getId())
        .username(userDetails.getUsername())
        .build();
    return ResponseEntity.ok(ApiResponse.success("Login successful", response));
  }
}
