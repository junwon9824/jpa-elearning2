package io.sample.learn.repository;

import io.sample.learn.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {



    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String name, String email);
    Optional<User> findByUsername(String name);
    Boolean existsByUsername(String name);
    Boolean existsByEmail(String email);

}