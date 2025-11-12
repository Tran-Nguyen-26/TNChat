package com.backend.app.service;

import com.backend.app.dto.request.UserRegisterRequest;
import com.backend.app.model.User;

public interface IAuthService {
  public User register(UserRegisterRequest request);
}
