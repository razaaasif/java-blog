package com.raza.blog.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

import com.raza.blog.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String roleName);

    List<Role> findByNameIn(List<String> roles);
}
