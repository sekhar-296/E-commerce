package com.niit.authenticationservice.domain;


import lombok.*;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
  private String userName;
  @Id
  private String email;
  private String mobileNo;
  private String dob;
  private String password;
  private Role role;
  @Embedded
  private Address address;


}
