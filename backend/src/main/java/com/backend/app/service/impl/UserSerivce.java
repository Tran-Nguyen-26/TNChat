package com.backend.app.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.backend.app.model.User;
import com.backend.app.repository.UserRepository;
import com.backend.app.service.IUserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserSerivce implements IUserService {
  
  private final UserRepository userRepository;

  @Override
  public User getUserByUsername(String username) {
    return userRepository.findByUsername(username)
      .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
  }

  @Override
  public User getUserById(Long userId) {
    return userRepository.findById(userId)
      .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
  }
}
