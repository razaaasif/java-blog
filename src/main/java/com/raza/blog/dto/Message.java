package com.raza.blog.dto;

import com.raza.blog.constants.SEVERITY;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private SEVERITY severity;
    private String message;
}
