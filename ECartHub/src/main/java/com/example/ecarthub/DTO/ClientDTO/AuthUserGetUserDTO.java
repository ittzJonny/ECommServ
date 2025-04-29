package com.example.ecarthub.DTO.ClientDTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class AuthUserGetUserDTO {
    private UUID id;
    private String email;
    private List<UserRoleDTO> rolesList= new ArrayList<>();
}
