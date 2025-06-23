package com.artcode.quarkus.user;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

    private String token;
    private String name;
    private String email;
    private Set<String> roles; // Optional, if you want to include user role
}
