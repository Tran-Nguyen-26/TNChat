package com.backend.app.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserDto {
  private Long id;
  private String fullname;
  private String username;
  private String email;
  private String phoneNumber;
  private LocalDate dob;
}
