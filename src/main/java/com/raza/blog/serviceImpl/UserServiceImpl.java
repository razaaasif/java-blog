package com.raza.blog.serviceImpl;

import com.raza.blog.entity.Role;
import com.raza.blog.entity.User;
import com.raza.blog.repository.interfaces.RoleRepository;
import com.raza.blog.repository.interfaces.UserRepository;
import com.raza.blog.service.UserService;
import com.raza.blog.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        return this.roleRepository.save(role);
    }

    @Override
    public User addRoleToUser(String username, String roleName) throws RoleNotFoundException {
        User user = this.userRepository.findByUsername(username).orElseThrow(() ->  new IllegalArgumentException(username));
        Role role = this.roleRepository.findByName(roleName).orElseThrow(() -> new IllegalArgumentException(roleName));
        user.getRoles().add(role);
        user = this.userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username).orElseThrow(()-> new IllegalArgumentException(username));
    }

    @Override
    public List<User> getUsers() {
        return Utils.nullSafeList(this.userRepository.findAll());
    }

    @Override
    public List<Role> getRoles() {
        return this.roleRepository.findAll();
    }
}
