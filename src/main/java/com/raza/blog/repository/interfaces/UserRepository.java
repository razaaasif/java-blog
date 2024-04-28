package com.raza.blog.repository.interfaces;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raza.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUsername(String username);

	Optional<User> findByUsernameOrEmail(String username , String Email);
}
