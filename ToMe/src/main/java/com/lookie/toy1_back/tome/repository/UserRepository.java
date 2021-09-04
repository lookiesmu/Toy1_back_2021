package com.lookie.toy1_back.tome.repository;

import com.lookie.toy1_back.tome.domain.User;
import com.lookie.toy1_back.tome.role.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
    Optional<UserRole> findByRole(String role);
}
