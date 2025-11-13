package com.backend.app.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

  @NotBlank(message = "fullname must be not blank")
  private String fullname;

  @NotBlank(message = "username must be not blank")
  private String username;

  @NotBlank(message = "email must be not blank")
  private String email;

  @NotBlank(message = "password must be not blank")
  private String password;

  @NotBlank(message = "confirm password must be not blank")
  private String confirmPassword;
  
  private String phoneNumber;
  private LocalDate dob;
}
