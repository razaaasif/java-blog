package com.raza.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raza.blog.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
