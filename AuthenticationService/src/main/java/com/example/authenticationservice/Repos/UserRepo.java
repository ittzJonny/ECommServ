package com.example.authenticationservice.Repos;

import com.example.authenticationservice.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepo extends JpaRepository<User, UUID> {

//    User save(User user);
        Optional<User> findByEmail(String email);

        Optional<User> findById(UUID id);

        Optional<User> findByEmailAndPassword(String email, String password);
}
