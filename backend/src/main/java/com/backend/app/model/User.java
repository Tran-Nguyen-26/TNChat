package com.backend.app.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String fullname;

  @Column(unique = true, nullable = false)
  private String username;

  @Column(unique = true, nullable = false)
  private String email;
  private String password;
  private String phoneNumber;

  @JsonFormat(pattern = "yyyy-mm-dd")
  private LocalDate dob;

  @OneToMany(mappedBy = "user")
  private List<Friend> friends;

  @OneToMany(mappedBy = "sender")
  private List<FriendRequest> sentRequests;

  @OneToMany(mappedBy = "receiver")
  private List<FriendRequest> receivedRequests;
}
