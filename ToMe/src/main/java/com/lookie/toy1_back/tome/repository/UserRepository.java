package com.lookie.toy1_back.tome.repository;

import com.lookie.toy1_back.tome.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
