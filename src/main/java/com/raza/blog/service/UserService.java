package com.raza.blog.service;

import com.raza.blog.entity.Role;
import com.raza.blog.entity.User;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    User addRoleToUser(String username , String roleName) throws RoleNotFoundException;
    User getUser(String username);
    List<User> getUsers();
    List<Role> getRoles();
}
