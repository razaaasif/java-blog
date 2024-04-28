package com.raza.blog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class UserDto {
    private String username;
    private String password;
    private String email;
    private String name;
    private List<String> roles;

}
