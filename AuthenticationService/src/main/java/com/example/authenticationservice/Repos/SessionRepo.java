package com.example.authenticationservice.Repos;

import com.example.authenticationservice.Models.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SessionRepo extends JpaRepository<Session, UUID> {
    Session save(Session session);

    Optional<Session> findByTokenAndUser_id(String token, UUID user_id);
    Optional<Session> findByToken(String token);
}
