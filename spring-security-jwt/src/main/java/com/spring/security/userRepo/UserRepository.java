package com.spring.security.userRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.security.user.UserInfo;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {

	Optional<UserInfo> findByUsername(String username);

}
