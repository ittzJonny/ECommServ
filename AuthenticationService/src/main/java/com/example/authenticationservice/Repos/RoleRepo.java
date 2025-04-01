package com.example.authenticationservice.Repos;

import com.example.authenticationservice.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RoleRepo extends JpaRepository<Role, UUID> {

    Optional<Role> findByRole(String role);
}
