package com.raza.blog.controller;

import com.raza.blog.config.JwtUtils;
import com.raza.blog.constants.SEVERITY;
import com.raza.blog.dto.*;
import com.raza.blog.entity.Role;
import com.raza.blog.entity.User;
import com.raza.blog.service.UserService;
import com.raza.blog.utils.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/auth")
public class AuthController {
    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> authenticate(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername() , loginRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if(userDetails != null){
            return ResponseEntity.ok(new JWTAuthResponse(jwtUtils.generateToken(userDetails),loginRequest.getUsername(), true ));
        } else {
            log.error("Username not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JWTAuthResponse(null,null, false ));        }
    }


    @PostMapping("/logout")
    public ResponseEntity<JWTAuthResponse> logout(@RequestBody LoginRequest loginRequest){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername() , loginRequest.getPassword()));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());
        if(userDetails != null){
            return ResponseEntity.ok(new JWTAuthResponse(jwtUtils.generateToken(userDetails),loginRequest.getUsername(), true ));
        } else {
            log.error("Username not found!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new JWTAuthResponse(null,null, false ));        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers(){
        List<User> users = this.userService.getUsers();
        if(!Objects.isNull(users)){
           return ResponseEntity.ok().body( users
                    .stream()
                    .map(this::createUserDto)
                    .collect(Collectors.toList())
           );
        }
         return null;
    }



    @PostMapping("/users/save")
    public ResponseEntity<Message> saveUser(@RequestBody UserDto userDto){
        try{
            User savedUser = this.userService.saveUser(this.createUser(userDto));
            return new ResponseEntity<Message>(new Message(SEVERITY.SUCCESS , "Registered Successfully") , HttpStatus.CREATED);
        } catch (Exception e){
            log.error("Error while creating user {} " , e.getMessage());
            return new ResponseEntity<Message>(new Message(SEVERITY.ERROR , e.getMessage()) , HttpStatus.CREATED);

        }

       }

    @PostMapping("/roles/save")
    public ResponseEntity<RoleDto> saveRole(@RequestBody RoleDto roleDto){
        log.debug("saveRole() {}", roleDto);

        Role role = this.userService.saveRole(new Role(null , roleDto.getName()));
        log.debug("saveRole() {}", role);
        return new ResponseEntity<RoleDto>(new RoleDto(role.getId(), role.getName()) , HttpStatus.CREATED);
    }

    @PostMapping("/users/add-role-to-user")
    public ResponseEntity<UserDto> addRoleToUser(@RequestBody UserRoleDto userRoleDto) throws RoleNotFoundException {
        List<Role> roles = this.userService.getRoles();
        User user = this.userService.addRoleToUser(userRoleDto.getUsername() , userRoleDto.getRole());
        return new ResponseEntity<UserDto>(this.createUserDto(user), HttpStatus.CREATED);
    }



    private Role createRole(String role) {
       return new Role(null , role);
    }


    private Set<Role> getRoles(List<String> roles) {
        if(Utils.isNullOrEmpty(roles)){
           List<Role> savedRoles = this.userService.getRoles();
           if(Utils.isNullOrEmpty(savedRoles)){
               Set<Role> filteredRoles = savedRoles.stream()
                       .filter(savedRole -> roles.stream()
                               .anyMatch(roleName -> roleName.equals(savedRole.getName())))
                       .collect(Collectors.toSet());
               if(filteredRoles.isEmpty()){
                   throw new IllegalArgumentException("Role is not correct");
               }
               return filteredRoles;
           }
        }
        return Collections.emptySet();
    }


    private User createUser(UserDto userDto) {
        return new User(null, userDto.getUsername() ,this.bCryptPasswordEncoder.encode(userDto.getPassword()), userDto.getEmail() , userDto.getName() , this.getRoles(userDto.getRoles()));
    }

    private UserDto createUserDto(User t) {
        return new UserDto( t.getUsername(),null,t.getEmail() ,  t.getName() , t.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
    }
}
