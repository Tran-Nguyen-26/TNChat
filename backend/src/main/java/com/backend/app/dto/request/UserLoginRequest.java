package com.backend.app.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserLoginRequest {
  @NotBlank(message = "username must be not blank")
  private String username;
  @NotBlank(message = "password must be not blank")
  private String password;
}
