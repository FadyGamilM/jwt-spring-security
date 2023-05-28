package com.fady.jwt_security.user.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fady.jwt_security.user.domain.AppUser;

public interface UserRepo extends JpaRepository<AppUser, Long> {
    @Query(value = "SELECT * FROM users WHERE email = ?1", nativeQuery = true)
    Optional<AppUser> FindByEmail(String email);

    @Query(value = "SELECT * FROM users WHERE username = ?1", nativeQuery = true)
    Optional<AppUser> FindByUsername(String username);
}