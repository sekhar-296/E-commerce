package com.niit.rabbitmq.domain;

import com.niit.authenticationservice.domain.Role;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String email;
    private String userName;
    private Role role;
}
