package com.souzs.scb.domain.dtos;

import com.souzs.scb.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private RoleDTO role;

    public UserDTO(User user) {
        id = user.getId();
        username = user.getUsernameByProfile();
        email = user.getEmail();
        role = new RoleDTO(user.getRole());
    }
}
