package com.example.flavorfulmelody.repository;

import com.example.flavorfulmelody.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserid(String userid);
    Optional<User> findByNickname(String nickname);

}
