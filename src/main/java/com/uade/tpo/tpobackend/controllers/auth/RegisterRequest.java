
package com.uade.tpo.tpobackend.controllers.auth;

import com.uade.tpo.tpobackend.entity.Role;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String nombre;
    private String pass;
    private Role role;
}
