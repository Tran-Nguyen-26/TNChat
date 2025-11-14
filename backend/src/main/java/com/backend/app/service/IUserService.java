package com.backend.app.service;

import com.backend.app.model.User;

public interface IUserService {
  public User getUserByUsername(String username);
  public User getUserById(Long userId);
}
