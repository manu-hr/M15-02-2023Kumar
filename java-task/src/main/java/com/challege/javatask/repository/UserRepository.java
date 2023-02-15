package com.challege.javatask.repository;

import com.challege.javatask.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username , String password);
}
