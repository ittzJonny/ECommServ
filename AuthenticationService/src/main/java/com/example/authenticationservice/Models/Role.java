package com.example.authenticationservice.Models;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.ToString;

@Data
@Entity
@ToString
public class Role extends BaseModel{

    private String role;
}
