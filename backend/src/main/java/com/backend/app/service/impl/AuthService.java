package com.backend.app.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.backend.app.dto.request.UserRegisterRequest;
import com.backend.app.exception.UserAlreadyExistsException;
import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;
import com.backend.app.service.IAuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService implements IAuthService {
  
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public User register(UserRegisterRequest request) {
    if (userRepository.existsByUsername(request.getUsername())) {
      throw new UserAlreadyExistsException("Username already exists: " + request.getUsername());
    } else if (userRepository.existsByEmail(request.getEmail())) {
      throw new UserAlreadyExistsException("Email already exists: " + request.getEmail());
    }
    User user = User.builder()
        .fullname(request.getFullname())
        .username(request.getUsername())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .phoneNumber(request.getPhoneNumber())
        .dob(request.getDob())
        .build();
    
    return userRepository.save(user);
  }
}
