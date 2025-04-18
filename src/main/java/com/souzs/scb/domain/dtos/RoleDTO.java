package com.souzs.scb.domain.dtos;

import com.souzs.scb.domain.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO(Role role) {
        id = role.getId();
        name = role.getName();
    }
}
